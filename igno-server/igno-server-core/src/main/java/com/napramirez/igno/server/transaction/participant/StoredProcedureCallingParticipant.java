package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.Log;
import org.jpos.util.NameRegistrar;

public abstract class StoredProcedureCallingParticipant
    extends Log
    implements TransactionParticipant
{
    private static final String CONNECTION_POOL_NAME = "connection.pool.forpost";

    protected Connection conn;

    protected CallableStatement cs;

    private String storedProcedure;

    public StoredProcedureCallingParticipant( String storedProcedure )
    {
        this.storedProcedure = storedProcedure;
    }

    public int prepare( long id, Serializable context )
    {
        try
        {
            DataSource ds = (DataSource) NameRegistrar.get( CONNECTION_POOL_NAME );
            conn = ds.getConnection();
            cs = conn.prepareCall( storedProcedure, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
        }
        catch ( Exception e )
        {
            return ABORTED;
        }

        return PREPARED;
    }

    public abstract void commit( long id, Serializable context );

    public void abort( long id, Serializable context )
    {
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
        }
        catch ( SQLException e )
        {
            // TODO: add elegant logging
            e.printStackTrace();
        }
    }
}
