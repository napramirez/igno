package com.napramirez.igno.client;

import org.jpos.iso.BaseChannel;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.channel.BASE24TCPChannel;
import org.jpos.iso.packager.BASE24Packager;
import org.jpos.q2.iso.IgnoChannelAdaptor;
import org.jpos.q2.iso.IgnoQMUX;
import org.jpos.q2.iso.QMUX;
import org.jpos.space.LocalSpace;
import org.jpos.space.SpaceFactory;

import com.napramirez.igno.common.message.MessageHeader;
import com.napramirez.igno.common.message.MessageTemplate;
import com.napramirez.igno.common.message.MessageTemplateFactory;

public abstract class TemplatedMessageSender
    implements Runnable
{
    private static final String DEFAULT_QUEUE_IN = "receive";

    private static final String DEFAULT_QUEUE_OUT = "send";

    private static final String DEFAULT_QUEUE_UNHANDLED = "unhandled";

    private static final String DEFAULT_HOST = "127.0.0.1";

    private static final int DEFAULT_PORT = 10000;

    private static final long DEFAULT_RECONNECT_DELAY = 10000;

    private static final long DEFAULT_TIMEOUT = 480000;

    private LocalSpace<?, ?> space;

    private IgnoChannelAdaptor channelAdaptor;

    private ISOChannel channel;

    private QMUX mux;

    private String id;

    private ISOMsg message;

    private TemplatedMessageSender( String id, ISOMsg message, MessageHeader header )
        throws Exception
    {
        this.id = id;
        this.message = message;

        space = (LocalSpace<?, ?>) SpaceFactory.getSpace( id );

        mux = new IgnoQMUX( space );
        mux.setInQueue( DEFAULT_QUEUE_OUT );
        mux.setOutQueue( DEFAULT_QUEUE_IN );
        mux.setUnhandledQueue( DEFAULT_QUEUE_UNHANDLED );
        mux.start();

        channel = new BASE24TCPChannel( DEFAULT_HOST, DEFAULT_PORT, new BASE24Packager() );
        ( (BaseChannel) channel ).setHeader( header.toString() );

        channelAdaptor = new IgnoChannelAdaptor( channel, space );
        channelAdaptor.setInQueue( DEFAULT_QUEUE_IN );
        channelAdaptor.setOutQueue( DEFAULT_QUEUE_OUT );
        channelAdaptor.setReconnectDelay( DEFAULT_RECONNECT_DELAY );
        channelAdaptor.start();
    }

    public TemplatedMessageSender( String id, MessageTemplate template, MessageHeader header )
        throws Exception
    {
        this( id, MessageTemplateFactory.INSTANCE.getMessageTemplate( template ), header );
    }

    public String getId()
    {
        return id;
    }

    public void setId( String id )
    {
        this.id = id;
    }

    public ISOMsg getMessage()
    {
        return message;
    }

    public void setMessage( ISOMsg message )
    {
        this.message = message;
    }

    public void run()
    {
        beforeSend( message );

        long startTime = System.currentTimeMillis();

        try
        {
            message.set( 11, ISOUtil.padleft( id, 6, '0' ) );
            message = mux.request( message, DEFAULT_TIMEOUT );
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

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println( ( message == null ? "Timed out: " : "Elapsed time: " ) + elapsedTime + "ms" );

        afterSend( message );
    }

    public void send()
    {
        new Thread( this ).start();
    }

    public abstract void beforeSend( ISOMsg request );

    public abstract void afterSend( ISOMsg response );
}
