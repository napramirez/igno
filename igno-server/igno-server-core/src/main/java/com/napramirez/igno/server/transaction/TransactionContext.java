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
    private static final long serialVersionUID = 7514149995022300940L;

    public enum ContextKey
    {
        PRODUCT_INDICATOR,
        REQUEST_MESSAGE,
        RESPONSE_MESSAGE,
        ISO_MESSAGE_SOURCE,
        DB_CONNECTION,
        DB_STATEMENT
    }

    private transient Map tmap;

    private Map map;

    public TransactionContext()
    {
        tmap = Collections.synchronizedMap( new LinkedHashMap() );
        map = Collections.synchronizedMap( new LinkedHashMap() );
    }

    public void put( ContextKey key, Object value )
    {
        map.put( key, value );
    }

    public Object get( ContextKey key )
    {
        return map.get( key );
    }

    public Object remove( ContextKey key )
    {
        return map.remove( key );
    }

    public void tput( ContextKey key, Object value )
    {
        tmap.put( key, value );
    }

    public Object tget( ContextKey key )
    {
        return tmap.get( key );
    }

    public Object tremove( ContextKey key )
    {
        return tmap.remove( key );
    }
}
