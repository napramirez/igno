package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.Log;
import org.jpos.util.NameRegistrar;
import org.jpos.util.NameRegistrar.NotFoundException;

import com.napramirez.igno.server.message.field.Track2Data;
import com.napramirez.igno.server.message.field.constants.ResponseCode;
import com.napramirez.igno.server.transaction.TransactionContext;

public class AuthorizationProcessingParticipant
    extends Log
    implements TransactionParticipant
{
    private static final String CONNECTION_POOL_NAME = "connection.pool.forpost";

    protected Connection conn;

    protected CallableStatement cs;

    private String storedProcedure = "{ CALL pg_authorize(?, ?, ?, ?) }";

    public int prepare( long id, Serializable context )
    {
        long startTime = System.currentTimeMillis();

        try
        {
            DataSource ds = (DataSource) NameRegistrar.get( CONNECTION_POOL_NAME );
            conn = ds.getConnection();
            cs = conn.prepareCall( storedProcedure, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
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

        TransactionContext ctx = (TransactionContext) context;
        ISOMsg request = (ISOMsg) ctx.get( "request" );

        Long pan = getPAN( request );
        double amount = Double.valueOf( request.getString( 4 ) );

        double newBalance;
        boolean isAuthorized;
        try
        {
            cs.setLong( 1, pan );
            cs.setDouble( 2, amount );
            cs.registerOutParameter( 3, Types.NUMERIC );
            cs.registerOutParameter( 4, Types.BOOLEAN );

            cs.execute();

            newBalance = cs.getDouble( 3 );
            isAuthorized = cs.getBoolean( 4 );

            cs.close();
            conn.close();
        }
        catch ( SQLException e )
        {
            error( e );
            return ABORTED;
        }

        ISOMsg response = (ISOMsg) request.clone();
        try
        {
            response.setResponseMTI();

            response.set( 38, "A00000" );
            response.set( 39, isAuthorized ? ResponseCode.POS.APPROVED.toString() : ResponseCode.POS.DECLINE.toString() );
        }
        catch ( ISOException e )
        {
            error( e );
            return ABORTED;
        }

        ctx.put( "response", response );

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        info( "pg_authorize( " + pan + ", " + amount + ", " + newBalance + ", " + isAuthorized + " ), " + elapsedTime
            + "ms" );

        return PREPARED;
    }

    public void commit( long id, Serializable context )
    {
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

    private long getPAN( ISOMsg message )
    {
        String panString = message.getString( 2 );
        if ( StringUtils.isNotBlank( panString ) )
        {
            return Long.valueOf( panString );
        }
        else
        {
            String track2DataString = message.getString( 35 );
            if ( StringUtils.isNotBlank( track2DataString ) )
            {
                Track2Data track2Data = new Track2Data( track2DataString );
                return Long.valueOf( track2Data.getPan() );
            }
        }

        return 0;
    }
}
