package com.napramirez.igno.server.common.constants;

/**
 * ProductIndicator
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public final class ProductIndicator
{
    public static String KEY = "product-indicator";

    public static String NETWORK_MANAGEMENT_MESSAGE = "network-management-messages";

    public static String ATM_MESSAGE = "atm-messages";

    public static String POS_MESSAGE = "pos-messages";

    public static String FROM_HOST_MAINTENANCE_MESSAGE = "from-host-maintenance-message";

    public static boolean isNetworkManagementMessage( String productIndicator )
    {
        return NETWORK_MANAGEMENT_MESSAGE.equals( productIndicator );
    }

    public static boolean isATMMessage( String productIndicator )
    {
        return ATM_MESSAGE.equals( productIndicator );
    }

    public static boolean isPOSMessage( String productIndicator )
    {
        return POS_MESSAGE.equals( productIndicator );
    }

    public static boolean isFHMMessage( String productIndicator )
    {
        return FROM_HOST_MAINTENANCE_MESSAGE.equals( productIndicator );
    }
}
