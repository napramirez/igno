package com.napramirez.igno.server.transaction.participant;

import java.io.IOException;
import java.io.Serializable;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.Log;

import com.napramirez.igno.server.transaction.TransactionContext;
import com.napramirez.igno.server.transaction.TransactionContext.ContextKey;

/**
 * ResponseSendingParticipant sends the response constructed by the preceding participants.
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class ResponseSendingParticipant
    extends Log
    implements TransactionParticipant
{
    public int prepare( long id, Serializable context )
    {
        TransactionContext ctx = (TransactionContext) context;

        ISOSource source = (ISOSource) ctx.tget( ContextKey.ISO_MESSAGE_SOURCE );
        if ( source == null )
        {
            error( "ISO Message Source is null!" );
            return ABORTED;
        }

        ISOMsg response = (ISOMsg) ctx.get( ContextKey.RESPONSE_MESSAGE );
        if ( response == null )
        {
            error( "ISO Message Response is null!" );
            return ABORTED;
        }

        try
        {
            if ( !response.isResponse() )
            {
                response.setResponseMTI();
            }
            source.send( response );
        }
        catch ( ISOException isoe )
        {
            error( isoe );
            return ABORTED;
        }
        catch ( IOException ioe )
        {
            error( ioe );
            return ABORTED;
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
