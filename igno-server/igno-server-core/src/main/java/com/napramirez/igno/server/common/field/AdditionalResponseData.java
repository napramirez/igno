package com.napramirez.igno.server.common.field;

/**
 * AdditionalResponseData - Field 044 in FIS ISO Specifications
 *
 * POS
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class AdditionalResponseData
{
    /**
     * AddressVerificationStatus - Position 4
     */
    public enum AddressVerificationStatus
    {
        CONDITION_NOT_SET( "0" ),
        ADDRESS_MATCHES_ZIP_DOES_NOT( "A" ),
        MESSAGE_CONTAINS_CONTENT_ERROR( "E" ),
        NEITHER_ADDRESS_NOR_ZIP_MATCHES( "N" ),
        RETRY( "R" ),
        NOT_APPLICABLE_SEE_NOTE( "S" ),
        ADDRESS_INFO_UNAVAILABLE( "U" ),
        NOT_APPLICABLE_SEE_NOTE2( "W" ),
        NOT_APPLICABLE_SEE_NOTE3( "X" ),
        ADDRESS_AND_ZIP_MATCH( "Y" ),
        ZIP_MATCHES_ADDRESS_DOES_NOT( "Z" );

        private String code;

        AddressVerificationStatus( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    private static final int FIELD_LENGTH = 4;

    private String fieldLengthIndicator;

    private String responseData;

    private String addressVerficationStatus;

    public AdditionalResponseData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Additional Response Data field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 2 );
        responseData = fieldStringValue.substring( 2, 3 );
        addressVerficationStatus = fieldStringValue.substring( 3 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getResponseData()
    {
        return responseData;
    }

    public void setResponseData( String responseData )
    {
        this.responseData = responseData;
    }

    public String getAddressVerficationStatus()
    {
        return addressVerficationStatus;
    }

    public void setAddressVerficationStatus( String addressVerficationStatus )
    {
        this.addressVerficationStatus = addressVerficationStatus;
    }
}
