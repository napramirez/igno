package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;

import org.jpos.transaction.GroupSelector;
import org.jpos.transaction.TransactionParticipant;

/**
 * MessageSwitchParticipant routes a message to the appropriate group.
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class MessageSwitchParticipant
    implements TransactionParticipant, GroupSelector
{
    public int prepare( long id, Serializable context )
    {
        return PREPARED | READONLY | NO_JOIN;
    }

    public void commit( long id, Serializable context )
    {
    }

    public void abort( long id, Serializable context )
    {
    }

    public String select( long id, Serializable context )
    {
        return "test-group";
    }
}
