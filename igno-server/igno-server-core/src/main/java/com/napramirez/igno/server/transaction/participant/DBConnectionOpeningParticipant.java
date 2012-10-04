package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.Log;
import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;

import com.napramirez.igno.server.transaction.TransactionContext;
import com.napramirez.igno.server.transaction.TransactionContext.ContextKey;

/**
 * DBConnectionOpeningParticipant
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class DBConnectionOpeningParticipant
    extends Log
    implements TransactionParticipant, Configurable
{
    private Configuration cfg;

    public void setConfiguration( Configuration cfg )
        throws ConfigurationException
    {
        this.cfg = cfg;
    }

    public int prepare( long id, Serializable context )
    {
        String CONNECTION_POOL_NAME = "connection.pool.forpost";
        Connection conn = null;
        CallableStatement cs = null;
        String storedProcedure = cfg.get( "storedProcedure" );

        try
        {
            DataSource ds = (DataSource) NameRegistrar.get( CONNECTION_POOL_NAME );
            conn = ds.getConnection();
            cs = conn.prepareCall( storedProcedure, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );

            TransactionContext ctx = (TransactionContext) context;
            ctx.tput( ContextKey.DB_CONNECTION, conn );
            ctx.tput( ContextKey.DB_STATEMENT, cs );
        }
        catch ( NotFoundException e )
        {
            error( e );
            return ABORTED;
        }
        catch ( SQLException e )
        {
            error( e );
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
