package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.transaction.AbortParticipant;
import org.jpos.util.Log;

import com.napramirez.igno.server.transaction.TransactionContext;
import com.napramirez.igno.server.transaction.TransactionContext.ContextKey;

/**
 * MessageRejectingParticipant echoes the request to the client, turning it to a 9xxx message in the process.
 * 
 * TODO: Fix client in order to handle 9xxx, as it expects only valid responses.
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class MessageRejectingParticipant
    extends Log
    implements AbortParticipant
{
    private static final String MTI_BIT_ONE_REJECT = "9";

    public int prepare( long id, Serializable context )
    {
        return PREPARED | READONLY;
    }

    public void commit( long id, Serializable context )
    {
    }

    public void abort( long id, Serializable context )
    {
    }

    public int prepareForAbort( long id, Serializable context )
    {
        TransactionContext ctx = (TransactionContext) context;
        ISOMsg message = (ISOMsg) ctx.get( ContextKey.REQUEST_MESSAGE );
        ISOSource source = (ISOSource) ctx.tget( ContextKey.ISO_MESSAGE_SOURCE );

        try
        {
            // FIXME: tampering the mti to a value the client does not expect will leave it waiting, e.g. 0200 to 9200
            // message.setMTI( MTI_BIT_ONE_REJECT.concat( message.getMTI().substring( 1 ) ) );
            message.setResponseMTI();
            source.send( message );
        }
        catch ( Exception e )
        {
            error( e );
            return 1;
        }

        return 0;
    }
}
