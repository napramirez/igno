package org.jpos.q2.iso;

import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOChannel;
import org.jpos.q2.iso.ChannelAdaptor;
import org.jpos.space.LocalSpace;

/**
 * IgnoChannelAdaptor wraps the existing ChannelAdaptor so it may be used outside of the Q2 environment.
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class IgnoChannelAdaptor
    extends ChannelAdaptor
{
    private ISOChannel tmpChannel;

    @SuppressWarnings( "rawtypes" )
    public IgnoChannelAdaptor( ISOChannel channel, LocalSpace space )
    {
        setName( getClass().getName() );
        setState( STOPPED );
        resetCounters();

        this.tmpChannel = channel;
        this.sp = space;
        this.ready = getName() + ".ready";
        this.reconnect = getName() + ".reconnect";
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public synchronized void setInQueue( String in )
    {
        String old = this.in;
        this.in = in;
        if ( old != null )
            sp.out( old, new Object() );

        setModified( true );
    }

    @Override
    public synchronized void setOutQueue( String out )
    {
        this.out = out;
        setModified( true );
    }

    @Override
    public synchronized void setReconnectDelay( long delay )
    {
        this.delay = delay;
        setModified( true );
    }

    @Override
    protected ISOChannel initChannel()
        throws ConfigurationException
    {
        return tmpChannel;
    }
}
