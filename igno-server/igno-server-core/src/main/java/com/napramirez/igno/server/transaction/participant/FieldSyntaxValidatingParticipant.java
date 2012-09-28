package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;

import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOMsg;
import org.jpos.q2.QBeanSupport;
import org.jpos.transaction.TransactionParticipant;

import com.napramirez.igno.server.message.field.constants.ProductIndicator;
import com.napramirez.igno.server.message.field.validation.FISFieldDictionary;
import com.napramirez.igno.server.message.field.validation.Field;
import com.napramirez.igno.server.message.field.validation.FieldDefinition;
import com.napramirez.igno.server.message.field.validation.FieldDictionary;
import com.napramirez.igno.server.message.field.validation.FieldKey;
import com.napramirez.igno.server.message.field.validation.FieldSyntaxValidator;
import com.napramirez.igno.server.message.field.validation.FieldValidationException;
import com.napramirez.igno.server.message.field.validation.FieldValidator;
import com.napramirez.igno.server.transaction.TransactionContext;

/**
 * FieldSyntaxValidatingParticipant - checks if all the fields follow the definitions
 *
 * TODO: hook the validator and dictionary to the Q2 context
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class FieldSyntaxValidatingParticipant
    extends QBeanSupport
    implements TransactionParticipant
{
    // private static final String PROPERTY_FIELD_VALIDATOR = "field-validator";

    // private static final String PROPERTY_FIELD_DICTIONARY = "field-dictionary";

    private FieldValidator validator;

    private FieldDictionary dictionary;

    public void setConfiguration( Configuration cfg )
        throws ConfigurationException
    {
        // this.validator = (FieldValidator) getFactory().newInstance( cfg.get( PROPERTY_FIELD_VALIDATOR ) );
        // this.dictionary = (FieldDictionary) getFactory().newInstance( cfg.get( PROPERTY_FIELD_DICTIONARY ) );
        this.validator = new FieldSyntaxValidator();
        this.dictionary = new FISFieldDictionary();
    }

    public int prepare( long id, Serializable context )
    {
        TransactionContext ctx = (TransactionContext) context;
        ISOMsg request = (ISOMsg) ctx.get( "request" );
        ProductIndicator pi = (ProductIndicator) ctx.get( ProductIndicator.KEY );

        String fieldStringValue = null;
        for ( int fieldIndex = 0; fieldIndex <= request.getMaxField(); fieldIndex++ )
        {
            if ( request.hasField( fieldIndex ) )
            {
                fieldStringValue = request.getString( fieldIndex );

                FieldKey fieldKey = new FieldKey( fieldIndex, pi );
                FieldDefinition fieldDefinition = dictionary.getDefinition( fieldKey );
                if ( fieldDefinition == null )
                {
                    getLog().error( "Field Definition for '" + fieldKey + "' not found!" );
                    return ABORTED;
                }

                Field field = new Field( fieldKey, fieldDefinition, fieldStringValue );

                try
                {
                    validator.validate( field );
                }
                catch ( FieldValidationException e )
                {
                    getLog().error( e );
                    return ABORTED;
                }
            }
        }

        return PREPARED;
    }

    public void commit( long id, Serializable context )
    {
    }

    public void abort( long id, Serializable context )
    {
    }
}
