package com.napramirez.igno.server.message.field.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * ProductIndicator
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public enum ProductIndicator
{
    KEY( "pi" ),
    NETWORK_MANAGEMENT_MESSAGE( "n" ),
    ATM_MESSAGE( "a" ),
    POS_MESSAGE( "p" ),
    FROM_HOST_MAINTENANCE_MESSAGE( "f" );
    
    private static final Map<String, ProductIndicator> lookup = new HashMap<String, ProductIndicator>();
    
    static
    {
        for ( ProductIndicator type : ProductIndicator.values() )
        {
            lookup.put( type.getSuffix(), type );
        }
    }

    private String suffix;

    ProductIndicator( String suffix )
    {
        this.suffix = suffix;
    }

    public String getSuffix()
    {
        return suffix;
    }
    
    public static ProductIndicator get( String key )
    {
        return lookup.get( key );
    }
}
