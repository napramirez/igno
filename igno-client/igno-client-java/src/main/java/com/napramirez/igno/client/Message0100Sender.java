package com.napramirez.igno.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;

import com.napramirez.igno.common.message.MessageHeader;
import com.napramirez.igno.common.message.MessageTemplate;

public class Message0100Sender
    extends TemplatedMessageSender
{
    private static Integer rx = 0;

    private static Integer tx = 0;

    private static Long min = Long.MAX_VALUE;

    private static Long max = 0L;

    private static Long totalTime = 0L;

    public Message0100Sender( String id )
        throws Exception
    {
        super( id, MessageTemplate.AUTHORIZATION, MessageHeader.POS_MESSAGE );
    }

    public void beforeSend( ISOMsg request )
    {
        System.out.println( "Sending authorization request '" + getId() + "'..." );

        synchronized ( tx )
        {
            tx++;
        }
    }

    public void afterSend( ISOMsg response, long elapsedTime )
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

            synchronized ( rx )
            {
                rx++;
            }

            synchronized ( totalTime )
            {
                totalTime += elapsedTime;
            }

            synchronized ( min )
            {
                min = elapsedTime < min ? elapsedTime : min;
            }

            synchronized ( max )
            {
                max = elapsedTime > max ? elapsedTime : max;
            }
        }
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
        ExecutorService executor = Executors.newCachedThreadPool();

        int messageCount = 0;
        for ( String account : accountsAndAmounts.keySet() )
        {
            Message0100Sender sender = new Message0100Sender( "" + messageCount );

            ISOMsg request = sender.getMessage();
            request.set( 2, account );

            BigDecimal amount = new BigDecimal( accountsAndAmounts.get( account ) );
            request.set( 4, ISOUtil.zeropad( amount.scaleByPowerOfTen( 2 ).longValue(), 12 ) );

            executor.execute( sender );

            messageCount++;
        }

        executor.shutdown();

        try
        {
            executor.awaitTermination( Long.MAX_VALUE, TimeUnit.NANOSECONDS );
        }
        catch ( InterruptedException e )
        {
        }

        System.out.println( "Transmitted: " + tx + ", Received: " + rx );
        System.out.println( "Min: " + min + "ms, Ave: " + ( totalTime / rx ) + "ms, Max: " + max + "ms" );
    }
}
