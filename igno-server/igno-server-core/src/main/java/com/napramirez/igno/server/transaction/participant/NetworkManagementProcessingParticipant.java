package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;

import org.jpos.iso.ISOMsg;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.Log;

import com.napramirez.igno.server.message.field.constants.NetworkManagementInformationCode;
import com.napramirez.igno.server.message.field.constants.ResponseCode;
import com.napramirez.igno.server.transaction.TransactionContext;
import com.napramirez.igno.server.transaction.TransactionContext.ContextKey;

/**
 * NetworkManagementProcessingParticipant
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class NetworkManagementProcessingParticipant
    extends Log
    implements TransactionParticipant
{
    public int prepare( long id, Serializable context )
    {
        return PREPARED | READONLY;
    }

    public void commit( long id, Serializable context )
    {
        try
        {
            TransactionContext ctx = (TransactionContext) context;
            ISOMsg request = (ISOMsg) ctx.get( ContextKey.REQUEST_MESSAGE );

            String netManCode = request.getString( 70 );

            if ( NetworkManagementInformationCode.LOGON.toString().equals( netManCode ) )
            {
                info( "Log on" );
            }
            else if ( NetworkManagementInformationCode.LOGOFF.toString().equals( netManCode ) )
            {
                info( "Log off" );
            }
            else if ( NetworkManagementInformationCode.ECHO_TEST.toString().equals( netManCode ) )
            {
                info( "Echo Test" );
            }

            ISOMsg response = (ISOMsg) request.clone();
            response.setResponseMTI();
            response.set( 39, ResponseCode.POS.APPROVED.toString() );

            ctx.put( ContextKey.RESPONSE_MESSAGE, response );
        }
        catch ( Exception e )
        {
            error( e );
        }
    }

    public void abort( long id, Serializable context )
    {
    }
}
