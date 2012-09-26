package com.napramirez.igno.server.message.field.fhm;

/**
 * BaseSegment - Field 120 in FIS ISO Specifications
 * 
 * File Update (NEG)
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class BaseSegment
{
    /**
     * Reason Code - Position 6-7
     */
    public enum ReasonCode
    {
        ACCOUNT_OPEN( "0" ),
        LOST_CARD( "1" ),
        STOLEN_CARD( "2" ),
        REFERRAL( "3" ),
        DENIAL( "5" ),
        SIGNATURE_RESTRICTION( "6" ),
        COUNTRY_CLUB( "7" ),
        CARD_EXPIRED( "8" ),
        COMMERCIAL( "9" ),
        VIP( "10" ),
        ACCOUNT_CLOSED( "11" );

        private String code;

        private ReasonCode( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    /**
     * Capture Code - Position 8
     */
    public enum CaptureCode
    {
        RETURN_THE_CARD( "0" ),
        CAPTURE_THE_CARD( "1" );

        private String code;

        private CaptureCode( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    private static final int FIELD_LENGTH = 18;

    private String fieldLengthIndicator;

    private String cardType;

    private String reasonCode;

    private String captureCode;

    private String recordAddDate;

    private String recordExpirationDate;

    public BaseSegment( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Base Segment field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        cardType = fieldStringValue.substring( 3, 5 );
        reasonCode = fieldStringValue.substring( 5, 7 );
        captureCode = fieldStringValue.substring( 7, 8 );
        recordAddDate = fieldStringValue.substring( 8, 14 );
        recordExpirationDate = fieldStringValue.substring( 14 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getCardType()
    {
        return cardType;
    }

    public void setCardType( String cardType )
    {
        this.cardType = cardType;
    }

    public String getReasonCode()
    {
        return reasonCode;
    }

    public void setReasonCode( String reasonCode )
    {
        this.reasonCode = reasonCode;
    }

    public String getCaptureCode()
    {
        return captureCode;
    }

    public void setCaptureCode( String captureCode )
    {
        this.captureCode = captureCode;
    }

    public String getRecordAddDate()
    {
        return recordAddDate;
    }

    public void setRecordAddDate( String recordAddDate )
    {
        this.recordAddDate = recordAddDate;
    }

    public String getRecordExpirationDate()
    {
        return recordExpirationDate;
    }

    public void setRecordExpirationDate( String recordExpirationDate )
    {
        this.recordExpirationDate = recordExpirationDate;
    }
}
