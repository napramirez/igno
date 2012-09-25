package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.Log;

import com.napramirez.igno.server.transaction.TransactionContext;

/**
 * POSFinancialTransactionProcessingParticipant
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class POSFinancialTransactionProcessingParticipant
    extends Log
    implements TransactionParticipant
{
    public int prepare( long id, Serializable context )
    {
        return PREPARED;
    }

    public void abort( long id, Serializable context )
    {
    }

    public void commit( long id, Serializable context )
    {
        long startTime = System.currentTimeMillis();

        try
        {
            TransactionContext ctx = (TransactionContext) context;
            ISOMsg request = (ISOMsg) ctx.get( "request" );

            ISOMsg response = (ISOMsg) request.clone();
            response.setResponseMTI();

            ctx.put( "response", response );

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            info( "Elapsed time: " + elapsedTime + "ms" );
        }
        catch ( ISOException e )
        {
            throw new RuntimeException( e );
        }
    }
}
