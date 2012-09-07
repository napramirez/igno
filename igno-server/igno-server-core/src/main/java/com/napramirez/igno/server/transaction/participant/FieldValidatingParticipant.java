package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.transaction.TransactionParticipant;

/**
 * FieldValidatingParticipant will check if all the required fields are present in the message.
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class FieldValidatingParticipant
    implements TransactionParticipant, Configurable
{
    private String[] requiredFields;

    public void setConfiguration( Configuration cfg )
        throws ConfigurationException
    {
        String propRequiredFields = cfg.get( "requiredFields" );
        requiredFields = propRequiredFields.split( "," );
    }

    public int prepare( long id, Serializable context )
    {
        if ( requiredFields == null || requiredFields.length == 0 )
        {
            return ABORTED;
        }

        for ( String requiredField : requiredFields )
        {
            try
            {
                int fieldValue = Integer.parseInt( requiredField );

                if ( fieldValue < 0 )
                {
                    return ABORTED;
                }
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
        // TODO
    }

    public void abort( long id, Serializable context )
    {
    }
}
