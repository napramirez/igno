package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;
import java.sql.Types;

import org.jpos.iso.ISOMsg;

import com.napramirez.igno.server.transaction.TransactionContext;

public class AuthorizationProcessingParticipant
    extends StoredProcedureCallingParticipant
{
    public AuthorizationProcessingParticipant( String storedProcedure )
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

            Long accountNumber = Long.valueOf( request.getString( 2 ) );
            double amount = Double.valueOf( request.getString( 4 ) );

            cs.setLong( 1, accountNumber );
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
