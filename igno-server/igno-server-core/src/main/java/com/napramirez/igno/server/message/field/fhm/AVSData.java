package com.napramirez.igno.server.message.field.fhm;

/**
 * AVSData - Field 127 in FIS ISO Specifications
 *
 * File Update (CAF)
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class AVSData
{
    private static final int FIELD_LENGTH = 32;

    private String fieldLengthIndicator;

    private String addressInformation;

    private String zipCode;

    public AVSData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "AVS Data field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        addressInformation = fieldStringValue.substring( 3, 23 );
        zipCode = fieldStringValue.substring( 23 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getAddressInformation()
    {
        return addressInformation;
    }

    public void setAddressInformation( String addressInformation )
    {
        this.addressInformation = addressInformation;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode( String zipCode )
    {
        this.zipCode = zipCode;
    }
}
