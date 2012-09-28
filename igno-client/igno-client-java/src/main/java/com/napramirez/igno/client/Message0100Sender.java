package com.napramirez.igno.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.jpos.iso.BaseChannel;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.channel.BASE24TCPChannel;
import org.jpos.iso.packager.BASE24Packager;
import org.jpos.iso.packager.XMLPackager;
import org.jpos.q2.iso.IgnoChannelAdaptor;
import org.jpos.q2.iso.IgnoQMUX;
import org.jpos.q2.iso.QMUX;
import org.jpos.space.LocalSpace;
import org.jpos.space.SpaceFactory;

public class Message0100Sender
    implements Runnable
{
    private static final String DEFAULT_QUEUE_IN = "receive";

    private static final String DEFAULT_QUEUE_OUT = "send";

    private static final String DEFAULT_QUEUE_UNHANDLED = "unhandled";

    private static final String DEFAULT_HOST = "127.0.0.1";

    private static final int DEFAULT_PORT = 10000;

    private static final long DEFAULT_RECONNECT_DELAY = 10000;

    private static final long DEFAULT_TIMEOUT = 480000;

    private static final String HEADER_MASK = "ISOxx60000yz";

    private LocalSpace<?, ?> space;

    private IgnoChannelAdaptor channelAdaptor;

    private ISOChannel channel;

    private QMUX mux;

    private ISOMsg message;

    public Message0100Sender( ISOMsg message, String id )
        throws Exception
    {
        this.message = message;

        space = (LocalSpace<?, ?>) SpaceFactory.getSpace( id );

        mux = new IgnoQMUX( space );
        mux.setInQueue( DEFAULT_QUEUE_OUT );
        mux.setOutQueue( DEFAULT_QUEUE_IN );
        mux.setUnhandledQueue( DEFAULT_QUEUE_UNHANDLED );
        mux.start();

        channel = new BASE24TCPChannel( DEFAULT_HOST, DEFAULT_PORT, new BASE24Packager() );
        ( (BaseChannel) channel ).setHeader( HEADER_MASK );

        channelAdaptor = new IgnoChannelAdaptor( channel, space );
        channelAdaptor.setInQueue( DEFAULT_QUEUE_IN );
        channelAdaptor.setOutQueue( DEFAULT_QUEUE_OUT );
        channelAdaptor.setReconnectDelay( DEFAULT_RECONNECT_DELAY );
        channelAdaptor.start();
    }

    public void run()
    {
        long startTime = System.currentTimeMillis();
        System.out.println( "Authorizing " + message.getString( 4 ) + " for account " + message.getString( 2 ) + " ("
            + startTime + "ms)" );

        try
        {
            ISOMsg response = mux.request( message, DEFAULT_TIMEOUT );

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            String result = "TimedOut";
            if ( response != null )
            {
                if ( "00".equals( response.getString( 39 ) ) )
                {
                    result = "Approved";
                }
                else if ( "39".equals( response.getString( 39 ) ) )
                {
                    result = "Declined";
                }
                else
                {
                    result = "Unknown";
                }
            }
            System.out.println( result + "( " + response.getString( 2 ) + ", " + response.getString( 4 ) + " ) "
                + elapsedTime + "ms" );
        }
        catch ( ISOException e )
        {
            e.printStackTrace();
        }
        finally
        {
            mux.stop();
            channelAdaptor.stop();
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

    public static ISOMsg getMessage( InputStream is, ISOPackager packager )
        throws IOException, ISOException
    {
        try
        {
            byte[] b = new byte[is.available()];
            is.read( b );
            ISOMsg m = new ISOMsg();
            m.setPackager( packager );
            m.unpack( b );
            return m;
        }
        finally
        {
            is.close();
        }
    }

    public static void main( String[] args )
        throws Exception
    {
        Map<String, String> accountsAndAmounts = getAccountsAndAmounts( "data.csv" );

        ISOMsg requestTemplate =
            getMessage( Message0100Sender.class.getClassLoader().getResourceAsStream( "messages/request/0100_1.xml" ),
                        new XMLPackager() );
        requestTemplate.setPackager( new BASE24Packager() );

        int messageCount = 0;
        for ( String account : accountsAndAmounts.keySet() )
        {
            ISOMsg requestClone = (ISOMsg) requestTemplate.clone();
            requestClone.set( 11, ISOUtil.padleft( Integer.toString( messageCount ), 6, '0' ) );

            requestClone.set( 2, account );

            BigDecimal amount = new BigDecimal( accountsAndAmounts.get( account ) );

            requestClone.set( 4, ISOUtil.zeropad( amount.scaleByPowerOfTen( 2 ).longValue(), 12 ) );

            new Thread( new Message0100Sender( requestClone, "" + messageCount ) ).start();
            messageCount++;
        }
    }
}
