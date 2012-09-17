package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.TransactionParticipant;

import com.napramirez.igno.server.common.ValidateRequest;
import com.napramirez.igno.server.common.ValidateRequestHelper;
import com.napramirez.igno.server.transaction.TransactionContext;

/**
 * FieldValidatingParticipant will check if all the required fields are present in the message.
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class FieldValidatingParticipant
    implements TransactionParticipant, Configurable
{
    private Configuration cfg;

    private int[] requiredFields;
    private int[] conditionalFields;

    public void setConfiguration( Configuration cfg )
        throws ConfigurationException
    {
        this.cfg = cfg;
    }

    public int prepare( long id, Serializable context )
    {
        String propRequiredFields = cfg.get( "requiredFields" );
        if ( propRequiredFields == null || propRequiredFields.trim().length() == 0 )
        {
            return ABORTED;
        }

        String[] requiredFieldStrings = propRequiredFields.trim().split( ", " );

        if ( requiredFieldStrings == null || requiredFieldStrings.length == 0 )
        {
            return ABORTED;
        }

        requiredFields = new int[requiredFieldStrings.length];
        for ( int i = 0; i < requiredFieldStrings.length; i++ )
        {
            try
            {
                int fieldValue = Integer.parseInt( requiredFieldStrings[i].trim() );

                if ( fieldValue < 0 )
                {
                    return ABORTED;
                }

                requiredFields[i] = fieldValue;
            }
            catch ( NumberFormatException e )
            {
                return ABORTED;
            }
        }
        
        // prepares the rest of the fields
        String propCondtionalFields = cfg.get( "conditionalFields" );
        String[] conditionalFieldStrings = propCondtionalFields.trim().split( ", " );
        conditionalFields = new int[conditionalFieldStrings.length];
        for ( int i = 0; i < conditionalFieldStrings.length; i++ )
        {
            try
            {
                int fieldValue = Integer.parseInt( conditionalFieldStrings[i].trim() );
                
                if ( fieldValue < 0 )
                {
                    return ABORTED;
                }
                conditionalFields[i] = fieldValue;
            }
            catch ( NumberFormatException e )
            {
                return ABORTED;
            }
        }

        return PREPARED;
    }

    public void commit( long id, Serializable context )
    {
        TransactionContext ctx = ( TransactionContext ) context;
        ISOMsg isoMsg = ( ISOMsg ) ctx.get( "request" );
        try
        {
            for ( int requiredField : requiredFields )
            {
                String message = isoMsg.getString( requiredField );

                if ( StringUtils.isEmpty( message ) )
                {
                    throw new Exception( ValidateRequest.ErrorMsg.MISSING_FIELD );
                }
            }
            int[] fields = new int[requiredFields.length + conditionalFields.length];
            System.arraycopy( requiredFields, 0, fields, 0, requiredFields.length );
            System.arraycopy( conditionalFields, 0, fields, requiredFields.length, conditionalFields.length );
            
            ValidateRequestHelper.fieldValidation( fields, isoMsg );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    public void abort( long id, Serializable context )
    {
    }
    
}
