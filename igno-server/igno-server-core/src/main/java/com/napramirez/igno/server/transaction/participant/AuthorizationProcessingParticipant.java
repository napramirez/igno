package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;
import java.sql.Types;

import org.apache.commons.lang.StringUtils;
import org.jpos.iso.ISOMsg;

import com.napramirez.igno.server.common.field.Track2Data;
import com.napramirez.igno.server.transaction.TransactionContext;

public class AuthorizationProcessingParticipant
    extends StoredProcedureCallingParticipant
{
    public AuthorizationProcessingParticipant()
    {
        super( "{ CALL pg_authorize(?, ?, ?, ?) }" );
    }

    @Override
    public void commit( long id, Serializable context )
    {
        long startTime = System.currentTimeMillis();

        try
        {
            TransactionContext ctx = (TransactionContext) context;
            ISOMsg request = (ISOMsg) ctx.get( "request" );

            Long pan = null;

            String panString = request.getString( 2 );
            if ( StringUtils.isNotBlank( panString ) )
            {
                pan = Long.valueOf( panString );
            }
            else
            {
                String track2DataString = request.getString( 35 );
                if ( StringUtils.isNotBlank( track2DataString ) )
                {
                    Track2Data track2Data = new Track2Data( track2DataString );
                    pan = Long.valueOf( track2Data.getPan() );
                }
            }

            double amount = Double.valueOf( request.getString( 4 ) );

            cs.setLong( 1, pan );
            cs.setDouble( 2, amount );
            cs.registerOutParameter( 3, Types.NUMERIC );
            cs.registerOutParameter( 4, Types.BOOLEAN );

            cs.execute();

            double newBalance = cs.getDouble( 3 );
            boolean isAuthorized = cs.getBoolean( 4 );

            ISOMsg response = (ISOMsg) request.clone();
            response.setResponseMTI();

            ctx.put( "response", response );
        }
        catch ( Exception e )
        {
            error( e );
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println( "Processing time: " + elapsedTime + "ms" );
    }
}
