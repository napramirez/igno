package org.jpos.q2.iso;

import java.util.ArrayList;

import org.jpos.q2.iso.QMUX;
import org.jpos.space.LocalSpace;

/**
 * IgnoQMUX wraps the existing QMUX so it may be used outside of the Q2 environment.
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class IgnoQMUX
    extends QMUX
{
    @SuppressWarnings( "rawtypes" )
    public IgnoQMUX( LocalSpace space )
    {
        setName( getClass().getName() );
        setState( STOPPED );

        this.listeners = new ArrayList();
        this.sp = space;
        this.mtiMapping = new String[] { nomap, nomap, "0022446789" };
        this.key = new String[] { "41", "11" };
    }

    @Override
    public synchronized void setInQueue( String in )
    {
        this.in = in;
        setModified( true );
    }

    @Override
    public synchronized void setOutQueue( String out )
    {
        this.out = out;
        setModified( true );
    }

    @Override
    public synchronized void setUnhandledQueue( String unhandled )
    {
        this.unhandled = unhandled;
        setModified( true );
    }
}
