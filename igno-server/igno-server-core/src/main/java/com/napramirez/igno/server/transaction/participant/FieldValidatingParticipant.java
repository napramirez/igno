package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.TransactionParticipant;

import com.napramirez.igno.server.common.validate.RequestFormat;
import com.napramirez.igno.server.common.validate.RequestValidator;
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
                    throw new Exception( RequestFormat.ErrorMsg.MISSING_FIELD );
                }
            }
            // prepares the rest of the fields
            String propCondtionalFields = cfg.get( "conditionalFields" );
            String[] conditionalFieldStrings = propCondtionalFields.trim().split( ", " );
            List<Integer> cList = new ArrayList<Integer>();
            for ( int i = 0; i < conditionalFieldStrings.length; i++ )
            {
                int fieldValue = Integer.parseInt( conditionalFieldStrings[i].trim() );
                    
                if ( isoMsg.getString( fieldValue ) != null )
                {
                    cList.add(fieldValue);
                }
            }
            int[] fields = new int[requiredFields.length + cList.size()];
            System.arraycopy( requiredFields, 0, fields, 0, requiredFields.length );
            for ( int index = requiredFields.length, i = 0; i < cList.size(); index++, i++ )
            {
                fields[index] = cList.get(i); 
            }
            String pi = ( String ) ctx.get( "pi" );
            
            RequestValidator.validator( pi, fields, isoMsg);
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
