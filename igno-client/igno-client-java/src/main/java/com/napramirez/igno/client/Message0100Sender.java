package com.napramirez.igno.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;

import com.napramirez.igno.common.message.MessageHeader;
import com.napramirez.igno.common.message.MessageTemplate;

public class Message0100Sender
    extends TemplatedMessageSender
{
    public Message0100Sender( String id )
        throws Exception
    {
        super( id, MessageTemplate.AUTHORIZATION, MessageHeader.POS_MESSAGE );
    }

    public void beforeSend( ISOMsg request )
    {
        System.out.println( "Sending authorization request '" + getId() + "'..." );
    }

    public void afterSend( ISOMsg response )
    {
        if ( response != null )
        {
            StringBuilder sb = new StringBuilder( "Result for request '" + getId() + "' is: " );

            String resultCode = response.getString( 39 );
            if ( "00".equals( resultCode ) )
            {
                sb.append( "APPROVED" );
            }
            else if ( "39".equals( resultCode ) )
            {
                sb.append( "DENIED" );
            }
            else
            {
                sb.append( "UNKNOWN" );
            }

            System.out.println( sb.toString() );
        }

        System.out.println( "Complete." );
    }

    public static Map<String, String> getAccountsAndAmounts( String filename )
        throws IOException
    {
        Map<String, String> accountsAndAmounts = new HashMap<String, String>();

        InputStream is = Message0100Sender.class.getClassLoader().getResourceAsStream( filename );
        BufferedReader br = new BufferedReader( new InputStreamReader( is ) );

        String rowData = null;
        while ( ( rowData = br.readLine() ) != null )
        {
            if ( rowData.trim().isEmpty() || rowData.trim().startsWith( "#" ) )
            {
                continue;
            }

            String[] splitData = rowData.split( "," );
            String account = splitData[0].trim();
            String amount = splitData[1].trim();
            accountsAndAmounts.put( account, amount );
        }

        br.close();
        is.close();

        return accountsAndAmounts;
    }

    public static void main( String[] args )
        throws Exception
    {
        Map<String, String> accountsAndAmounts = getAccountsAndAmounts( "data.csv" );

        int messageCount = 0;
        for ( String account : accountsAndAmounts.keySet() )
        {
            Message0100Sender sender = new Message0100Sender( "" + messageCount );

            ISOMsg request = sender.getMessage();
            request.set( 2, account );

            BigDecimal amount = new BigDecimal( accountsAndAmounts.get( account ) );
            request.set( 4, ISOUtil.zeropad( amount.scaleByPowerOfTen( 2 ).longValue(), 12 ) );

            sender.send();

            messageCount++;
        }
    }
}
