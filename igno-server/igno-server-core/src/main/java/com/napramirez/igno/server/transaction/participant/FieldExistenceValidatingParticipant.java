package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.Log;

import com.napramirez.igno.server.transaction.TransactionContext;

/**
 * FieldExistenceValidatingParticipant - checks if all the required fields are present in the message
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class FieldExistenceValidatingParticipant
    extends Log
    implements TransactionParticipant, Configurable
{
    private static final String PROPERTY_REQUIRED_FIELDS = "requiredFields";

    private static final String DELIMITER_FIELDS = ", ";

    private Configuration cfg;

    public void setConfiguration( Configuration cfg )
        throws ConfigurationException
    {
        this.cfg = cfg;
    }

    public int prepare( long id, Serializable context )
    {
        String propRequiredFields = cfg.get( PROPERTY_REQUIRED_FIELDS );
        if ( propRequiredFields == null || propRequiredFields.trim().length() == 0 )
        {
            error( "No required fields configured." );
            return ABORTED;
        }

        String[] requiredFieldStrings = propRequiredFields.trim().split( DELIMITER_FIELDS );
        if ( requiredFieldStrings == null || requiredFieldStrings.length == 0 )
        {
            error( "No required fields found!" );
            return ABORTED;
        }

        TransactionContext ctx = (TransactionContext) context;
        ISOMsg request = (ISOMsg) ctx.get( "request" );
        for ( int i = 0; i < requiredFieldStrings.length; i++ )
        {
            int fieldIndex = 0;

            try
            {
                fieldIndex = Integer.parseInt( requiredFieldStrings[i].trim() );
            }
            catch ( NumberFormatException e )
            {
                error( "Unrecognized required field '" + fieldIndex + "'", e );
                return ABORTED;
            }

            if ( fieldIndex < 0 )
            {
                error( "Invalid required field '" + fieldIndex + "'" );
                return ABORTED;
            }

            if ( !request.hasField( fieldIndex ) )
            {
                error( "Required field '" + fieldIndex + "' missing." );
                return ABORTED;
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
