package com.napramirez.igno.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

/**
 * MessageSender takes in an input file that contains the filenames of ISO messages to send.
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class MessageSender
    implements Runnable
{
    private static final String DEFAULT_QUEUE_IN = "receive";

    private static final String DEFAULT_QUEUE_OUT = "send";

    private static final String DEFAULT_QUEUE_UNHANDLED = "unhandled";

    private static final String DEFAULT_HOST = "127.0.0.1";

    private static final int DEFAULT_PORT = 10000;

    private static final long DEFAULT_RECONNECT_DELAY = 10000;

    private static final long DEFAULT_TIMEOUT = 480000;

    private static final String HEADER_NETWORK_MANAGEMENT = "ISO006000000";

    private static final String HEADER_ATM = "ISO016000000";

    private static final String HEADER_POS = "ISO026000000";

    private static final String HEADER_FROM_HOST_MAINTENANCE = "ISO086000000";

    private LocalSpace<?, ?> space;

    private IgnoChannelAdaptor channelAdaptor;

    private ISOChannel channel;

    private QMUX mux;

    private ISOPackager packager;

    private ISOMsg message;

    /**
     * This is a hack. Headers cannot be defined using the mti. Default financial messages are ATM messages.
     */
    private boolean isAtm = true;

    public MessageSender( ISOMsg message, String id )
        throws Exception
    {
        this.message = message;

        space = (LocalSpace<?, ?>) SpaceFactory.getSpace( id );

        mux = new IgnoQMUX( space );
        mux.setInQueue( DEFAULT_QUEUE_OUT );
        mux.setOutQueue( DEFAULT_QUEUE_IN );
        mux.setUnhandledQueue( DEFAULT_QUEUE_UNHANDLED );
        mux.start();

        packager = new BASE24Packager();
        channel = new BASE24TCPChannel( DEFAULT_HOST, DEFAULT_PORT, packager );

        channelAdaptor = new IgnoChannelAdaptor( channel, space );
        channelAdaptor.setInQueue( DEFAULT_QUEUE_IN );
        channelAdaptor.setOutQueue( DEFAULT_QUEUE_OUT );
        channelAdaptor.setReconnectDelay( DEFAULT_RECONNECT_DELAY );
        channelAdaptor.start();
    }

    public void run()
    {
        long startTime = System.currentTimeMillis();
        System.out.println( "Sending message at " + startTime + "ms" );

        try
        {
            setChannelHeader( message.getMTI() );
            ISOMsg response = mux.request( message, DEFAULT_TIMEOUT );

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            if ( response != null )
            {
                System.out.println( "Round trip completed in " + elapsedTime + "ms" );
                // response.dump( System.out, "" );
            }
            else
            {
                System.out.println( "Request timed out. (" + elapsedTime + "ms)" );
            }
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

    private void setChannelHeader( String mti )
    {
        if ( mti.equals( "0800" ) )
        {
            ( (BaseChannel) channel ).setHeader( HEADER_NETWORK_MANAGEMENT );
        }
        else if ( mti.equals( "0300" ) )
        {
            ( (BaseChannel) channel ).setHeader( HEADER_FROM_HOST_MAINTENANCE );
        }
        else if ( isAtm )
        {
            ( (BaseChannel) channel ).setHeader( HEADER_ATM );
        }
        else
        {
            ( (BaseChannel) channel ).setHeader( HEADER_POS );
        }
    }

    public static List<String> getRequestFilenamesFromRequestFileList( String fileList )
        throws IOException
    {
        InputStream is = MessageSender.class.getClassLoader().getResourceAsStream( fileList );
        BufferedReader br = new BufferedReader( new InputStreamReader( is ) );

        List<String> filenames = new ArrayList<String>();
        String filename = null;
        while ( ( filename = br.readLine() ) != null )
        {
            if ( filename.trim().isEmpty() || filename.trim().startsWith( "#" ) )
            {
                continue;
            }

            filenames.add( filename.trim() );
        }

        return filenames;
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
        int ECHO_COUNT = 1;
        boolean isSimultaneous = true;
        String baseMessagePath = "messages/request/";

        int messageCount = 0;

        List<String> requestFilenameList = getRequestFilenamesFromRequestFileList( baseMessagePath + "requests.txt" );
        for ( String requestFilename : requestFilenameList )
        {
            ISOMsg request =
                getMessage( MessageSender.class.getClassLoader().getResourceAsStream( baseMessagePath + requestFilename ),
                            new XMLPackager() );
            request.setPackager( new BASE24Packager() );

            List<ISOMsg> requestClones = new ArrayList<ISOMsg>();
            for ( int i = 0; i < ECHO_COUNT; i++ )
            {
                ISOMsg requestClone = (ISOMsg) request.clone();
                requestClone.set( 11, ISOUtil.padleft( Integer.toString( messageCount ), 6, '0' ) );
                requestClones.add( requestClone );

                messageCount++;
            }

            for ( ISOMsg requestClone : requestClones )
            {
                String id = requestClone.toString();
                MessageSender tc = new MessageSender( requestClone, id );

                if ( isSimultaneous )
                {
                    new Thread( tc ).start();
                }
                else
                {
                    tc.run();
                }
            }
        }
    }
}
