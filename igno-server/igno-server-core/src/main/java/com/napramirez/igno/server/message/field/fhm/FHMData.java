package com.napramirez.igno.server.message.field.fhm;

/**
 * FHMData - Field 60 in FIS ISO Specification
 * 
 * @author ztorres
 *
 */
public class FHMData
{
    private static final int FIELD_LENGTH = 61;
    private String dpcNumber;                          // 4-7
    private String stationIndex;                       // 8-11
    private String logicalNetwork;                     // 12-15
    private String lastTransactionTimestampString;     // 16-29
    private String logIndicator;                       // 30
    private String completionRequired;                 // 31
    private String maintenanceUserGroup;               // 32-35
    private String maintenanceUserNumber;              // 36-43
    private String maintenanceUserStation;             // 44-47
    private String lastFileMaintenanceTimestampString; // 48-61
    
    public FHMData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "FHM Data is invalid" );
        }
        
        dpcNumber = fieldStringValue.substring( 3, 7 );
        stationIndex = fieldStringValue.substring( 7, 11 );
        logicalNetwork = fieldStringValue.substring( 11, 15 );
        lastTransactionTimestampString = fieldStringValue.substring( 15, 29 );
        logIndicator = fieldStringValue.substring( 29, 30 );
        completionRequired = fieldStringValue.substring( 30, 31 );
        maintenanceUserGroup = fieldStringValue.substring( 31, 35 );
        maintenanceUserNumber = fieldStringValue.substring( 35, 43 );
        maintenanceUserStation = fieldStringValue.substring( 43, 47 );
        lastFileMaintenanceTimestampString = fieldStringValue.substring( 47 );
    }
    
    public String getDpcNumber()
    {
        return dpcNumber;
    }
    public void setDpcNumber( String dpcNumber )
    {
        this.dpcNumber = dpcNumber;
    }
    public String getStationIndex()
    {
        return stationIndex;
    }
    public void setStationIndex( String stationIndex )
    {
        this.stationIndex = stationIndex;
    }
    public String getLogicalNetwork()
    {
        return logicalNetwork;
    }
    public void setLogicalNetwork( String logicalNetwork )
    {
        this.logicalNetwork = logicalNetwork;
    }
    public String getLastTransactionTimestampString()
    {
        return lastTransactionTimestampString;
    }
    public void setLastTransactionTimestampString( String lastTransactionTimestampString )
    {
        this.lastTransactionTimestampString = lastTransactionTimestampString;
    }
    public String getLogIndicator()
    {
        return logIndicator;
    }
    public void setLogIndicator( String logIndicator )
    {
        this.logIndicator = logIndicator;
    }
    public String getCompletionRequired()
    {
        return completionRequired;
    }
    public void setCompletionRequired( String completionRequired )
    {
        this.completionRequired = completionRequired;
    }
    public String getMaintenanceUserGroup()
    {
        return maintenanceUserGroup;
    }
    public void setMaintenanceUserGroup( String maintenanceUserGroup )
    {
        this.maintenanceUserGroup = maintenanceUserGroup;
    }
    public String getMaintenanceUserNumber()
    {
        return maintenanceUserNumber;
    }
    public void setMaintenanceUserNumber( String maintenanceUserNumber )
    {
        this.maintenanceUserNumber = maintenanceUserNumber;
    }
    public String getMaintenanceUserStation()
    {
        return maintenanceUserStation;
    }
    public void setMaintenanceUserStation( String maintenanceUserStation )
    {
        this.maintenanceUserStation = maintenanceUserStation;
    }
    public String getLastFileMaintenanceTimestampString()
    {
        return lastFileMaintenanceTimestampString;
    }
    public void setLastFileMaintenanceTimestampString( String lastFileMaintenanceTimestampString )
    {
        this.lastFileMaintenanceTimestampString = lastFileMaintenanceTimestampString;
    }
}