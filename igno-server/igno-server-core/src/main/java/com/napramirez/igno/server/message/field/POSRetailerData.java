package com.napramirez.igno.server.message.field;

/**
 * POS Retailer Data - Field 48 in FIS ISO Specification
 * 
 * @author ztorres
 *
 */
public class POSRetailerData
{
    private static final int FIELD_LENGTH = 30;
    private String retailerID;     // 4-22
    private String retailerGroup;  // 23-26
    private String retailerRegion; // 27-30
    
    public POSRetailerData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "POS Retailer Data is invalid" );
        }
        
        retailerID = fieldStringValue.substring( 3, 22 );
        retailerGroup = fieldStringValue.substring( 22, 26 );
        retailerRegion = fieldStringValue.substring( 26 );
    }

    public String getRetailerID()
    {
        return retailerID;
    }

    public void setRetailerID( String retailerID )
    {
        this.retailerID = retailerID;
    }

    public String getRetailerGroup()
    {
        return retailerGroup;
    }

    public void setRetailerGroup( String retailerGroup )
    {
        this.retailerGroup = retailerGroup;
    }

    public String getRetailerRegion()
    {
        return retailerRegion;
    }

    public void setRetailerRegion( String retailerRegion )
    {
        this.retailerRegion = retailerRegion;
    }
}