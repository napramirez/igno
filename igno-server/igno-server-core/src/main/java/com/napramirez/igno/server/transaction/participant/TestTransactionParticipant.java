package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import javax.sql.DataSource;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
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

    public int prepare( long id, Serializable context )
    {
        return PREPARED;
    }

    public void commit( long id, Serializable context )
    {
        long startTime = System.currentTimeMillis();

        try
        {
            ISOMsg message = (ISOMsg) ( (TransactionContext) context ).get( "request" );
            ISOSource source = (ISOSource) ( (TransactionContext) context ).tget( "isosource" );

            Long accountNumber = Long.valueOf( message.getString( 2 ) );
            double amount = Double.valueOf( message.getString( 4 ) );

            DataSource ds = (DataSource) NameRegistrar.get( CONNECTION_POOL_NAME );
            Connection conn = ds.getConnection();

            CallableStatement cs =
                conn.prepareCall( SPROC_BB_AUTHORIZE, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
            cs.setLong( 1, accountNumber );
            cs.setDouble( 2, amount );
            cs.registerOutParameter( 3, Types.NUMERIC );
            cs.registerOutParameter( 4, Types.BOOLEAN );

            cs.execute();

            double newBalance = cs.getDouble( 3 );
            boolean isAuthorized = cs.getBoolean( 4 );

            cs.close();
            conn.close();

            message.setResponseMTI();

            source.send( message );
        }
        catch ( Exception e )
        {
            error( e );
        }
        finally
        {

        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println( "Processing time: " + elapsedTime + "ms" );
    }

    public void abort( long id, Serializable context )
    {
    }
}
