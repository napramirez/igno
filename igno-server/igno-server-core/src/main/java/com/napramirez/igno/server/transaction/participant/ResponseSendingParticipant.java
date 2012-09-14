package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.transaction.TransactionParticipant;

import com.napramirez.igno.server.transaction.TransactionContext;

/**
 * ResponseSendingParticipant sends the response constructed by the preceding participants.
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class ResponseSendingParticipant
    implements TransactionParticipant
{
    public int prepare( long id, Serializable context )
    {
        TransactionContext ctx = (TransactionContext) context;
        if ( ctx == null )
        {
            return ABORTED;
        }
        else
        {
            ISOSource source = (ISOSource) ctx.tget( "isosource" );
            if ( source == null )
            {
                return ABORTED;
            }
        }

        return PREPARED;
    }

    public void commit( long id, Serializable context )
    {
        TransactionContext ctx = (TransactionContext) context;
        ISOMsg response = (ISOMsg) ctx.get( "response" );
        ISOSource source = (ISOSource) ctx.tget( "isosource" );

        try
        {
            if ( !response.isResponse() )
            {
                response.setResponseMTI();
            }
            source.send( response );
        }
        catch ( Exception e )
        {
            // TODO: add elegant logging
            e.printStackTrace();
        }
    }

    public void abort( long id, Serializable context )
    {
    }
}
