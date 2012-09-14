package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;

import org.jpos.transaction.TransactionParticipant;

/**
 * TransactionAbortingParticipant simply aborts the transaction to redirect instantly to MessageRejectingParticipant.
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class TransactionAbortingParticipant
    implements TransactionParticipant
{
    public int prepare( long id, Serializable context )
    {
        return ABORTED;
    }

    public void commit( long id, Serializable context )
    {
    }

    public void abort( long id, Serializable context )
    {
    }
}
