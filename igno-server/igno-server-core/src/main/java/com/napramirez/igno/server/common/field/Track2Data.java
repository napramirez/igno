package com.napramirez.igno.server.common.field;

/**
 * Track2Data - Field 035 in FIS ISO Specifications
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class Track2Data
{
    private static final String FIELD_SEPARATOR_EQUALS = "=";

    private static final String FIELD_SEPARATOR_D = "D";

    private String pan;

    private String countryCode;

    private String expirationDate;

    private String serviceCode;

    private String discretionaryData;

    public Track2Data( String fieldStringValue )
    {
        String[] fields = fieldStringValue.split( FIELD_SEPARATOR_EQUALS );
        pan = fields[0];
    }

    public String getPan()
    {
        return pan;
    }

    public void setPan( String pan )
    {
        this.pan = pan;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode( String countryCode )
    {
        this.countryCode = countryCode;
    }

    public String getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate( String expirationDate )
    {
        this.expirationDate = expirationDate;
    }

    public String getServiceCode()
    {
        return serviceCode;
    }

    public void setServiceCode( String serviceCode )
    {
        this.serviceCode = serviceCode;
    }

    public String getDiscretionaryData()
    {
        return discretionaryData;
    }

    public void setDiscretionaryData( String discretionaryData )
    {
        this.discretionaryData = discretionaryData;
    }
}
