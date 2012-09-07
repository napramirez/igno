package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.jpos.iso.ISOMsg;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.Log;
import org.jpos.util.NameRegistrar;

import com.napramirez.igno.server.transaction.TransactionContext;

public class TestTransactionParticipant
    extends Log
    implements TransactionParticipant
{
    private static final String CONNECTION_POOL_NAME = "connection.pool.forpost";

    private static final String SPROC_BB_AUTHORIZE = "{ CALL bb_authorize(?, ?, ?, ?) }";

    private Connection conn;

    private CallableStatement cs;

    public int prepare( long id, Serializable context )
    {
        try
        {
            DataSource ds = (DataSource) NameRegistrar.get( CONNECTION_POOL_NAME );
            conn = ds.getConnection();
            cs = conn.prepareCall( SPROC_BB_AUTHORIZE, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
        }
        catch ( Exception e )
        {
            return ABORTED;
        }

        return PREPARED;
    }

    public void commit( long id, Serializable context )
    {
        long startTime = System.currentTimeMillis();

        try
        {
            ISOMsg message = (ISOMsg) ( (TransactionContext) context ).get( "request" );

            Long accountNumber = Long.valueOf( message.getString( 2 ) );
            double amount = Double.valueOf( message.getString( 4 ) );

            cs.setLong( 1, accountNumber );
            cs.setDouble( 2, amount );
            cs.registerOutParameter( 3, Types.NUMERIC );
            cs.registerOutParameter( 4, Types.BOOLEAN );

            cs.execute();

            double newBalance = cs.getDouble( 3 );
            boolean isAuthorized = cs.getBoolean( 4 );

            cs.close();
            conn.close();
        }
        catch ( Exception e )
        {
            error( e );
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println( "Processing time: " + elapsedTime + "ms" );
    }

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
