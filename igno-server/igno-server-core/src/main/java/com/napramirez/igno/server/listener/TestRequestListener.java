package com.napramirez.igno.server.listener;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import javax.sql.DataSource;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.util.Log;
import org.jpos.util.NameRegistrar;

/**
 * TestRequestListener
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class TestRequestListener
    extends Log
    implements ISORequestListener
{
    private static final String CONNECTION_POOL_NAME = "connection.pool.forpost";

    private static final String SPROC_BB_AUTHORIZE = "{ CALL bb_authorize(?, ?, ?, ?) }";

    public boolean process( ISOSource source, ISOMsg message )
    {
        long startTime = System.currentTimeMillis();

        try
        {
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

        return true;
    }
}
