package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.Log;

import com.napramirez.igno.server.transaction.TransactionContext;
import com.napramirez.igno.server.transaction.TransactionContext.ContextKey;

/**
 * @author ztorres
 *
 */
public class ATMFinancailTransactionAdviceProcessingParticipant
    extends Log
    implements TransactionParticipant
{

    public int prepare( long id, Serializable context )
    {
        return PREPARED;
    }

    public void commit( long id, Serializable context )
    {
        long startTime = System.currentTimeMillis();

        try
        {
            TransactionContext ctx = (TransactionContext) context;
            ISOMsg request = (ISOMsg) ctx.get( ContextKey.REQUEST_MESSAGE );

            ISOMsg response = (ISOMsg) request.clone();
            response.setResponseMTI();

            ctx.put( ContextKey.RESPONSE_MESSAGE, response );

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            info( "Elapsed time: " + elapsedTime + "ms" );
        }
        catch ( ISOException e )
        {
            throw new RuntimeException( e );
        }
    }

    public void abort( long id, Serializable context )
    {
        // TODO Auto-generated method stub
    }

}
