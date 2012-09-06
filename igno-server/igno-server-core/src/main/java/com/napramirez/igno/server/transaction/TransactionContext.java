package com.napramirez.igno.server.transaction;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TransactionContext
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
@SuppressWarnings( { "unchecked", "rawtypes" } )
public class TransactionContext
    implements Serializable
{
    private static final long serialVersionUID = 1L;

    private transient Map tmap;

    private Map map;

    public TransactionContext()
    {
        tmap = Collections.synchronizedMap( new LinkedHashMap() );
        map = Collections.synchronizedMap( new LinkedHashMap() );
    }

    public void put( Object key, Object value )
    {
        map.put( key, value );
    }

    public Object get( Object key )
    {
        return map.get( key );
    }

    public Object remove( Object key )
    {
        return map.remove( key );
    }

    public void tput( Object key, Object value )
    {
        tmap.put( key, value );
    }

    public Object tget( Object key )
    {
        return tmap.get( key );
    }

    public Object tremove( Object key )
    {
        return tmap.remove( key );
    }
}
