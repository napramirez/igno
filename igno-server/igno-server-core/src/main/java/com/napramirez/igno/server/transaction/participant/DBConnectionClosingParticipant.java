package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.jpos.transaction.AbortParticipant;
import org.jpos.util.Log;

import com.napramirez.igno.server.transaction.TransactionContext;

/**
 * DBConnectionClosingParticipant
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class DBConnectionClosingParticipant
    extends Log
    implements AbortParticipant
{
    public int prepare( long id, Serializable context )
    {
        return closeAll( id, context, PREPARED, PREPARED );
    }

    public void commit( long id, Serializable context )
    {
    }

    public void abort( long id, Serializable context )
    {
    }

    public int prepareForAbort( long id, Serializable context )
    {
        return closeAll( id, context, 0, 1 );
    }

    private int closeAll( long id, Serializable context, int successResult, int failureResult )
    {
        TransactionContext ctx = (TransactionContext) context;
        Connection conn = (Connection) ctx.tget( "connection" );
        CallableStatement cs = (CallableStatement) ctx.tget( "statement" );

        try
        {
            if ( conn != null )
            {
                conn.close();
            }
            if ( cs != null )
            {
                cs.close();
            }

            return successResult;
        }
        catch ( SQLException e )
        {
            error( e );
            return failureResult;
        }
    }
}
