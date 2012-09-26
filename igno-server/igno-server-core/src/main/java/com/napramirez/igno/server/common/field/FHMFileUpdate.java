package com.napramirez.igno.server.common.field;

/**
 * File Update - Field 120 in FIS ISO Specification
 * 
 * @author ztorres
 *
 */
public class FHMFileUpdate
{
    
    public enum ReasonCode
    {
        ACOUNT_OPEN( "1" ),
        LOST_CARD( "2" ),
        STOLEN_CARD( "3" ),
        REFERRAL( "4" ),
        DENIAL( "5" ),
        SIGNATURE_RESTRICTION( "6" ),
        COURNTRY_CLUB( "7" ),
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
    
    public enum CaptureCode
    {
        RETURN_CARD( "0" ),
        CAPTURE_CARD( "1" );
        
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
    
    private static final int FIELD_LENGTH_INDICATOR_LENGTH = 3;
    private static final String NEG_FIELD_LENGTH_IDICATOR = "015";
    private static final int NEG_FIELD_LENGTH = 18;
    private static final int CAF_FIELD_LENGTH = 98;
    
    private String cardType; // 4-5
    
    // CAF
    private String cardStatus;           // 6
    private String pinOffset;            // 7-22
    private String expirationDateString; // 95-98
    
    // NEG: FLI = "015"
    private ReasonCode reasonCode;   // 6-7
    private CaptureCode captureCode; // 8
    private String recordAddDateString;             // 9-14
    private String recordExpirationDateString;      // 15-18
    
    public FHMFileUpdate( String fieldStringValue )
    {
        if ( fieldStringValue == null || ( fieldStringValue.length() != CAF_FIELD_LENGTH 
                && fieldStringValue.length() != NEG_FIELD_LENGTH ) )
        {
            throw new IllegalArgumentException( "FHM File Update message is invalid" );
        }
        
        cardType = fieldStringValue.substring( 3, 5 );
        String fli = fieldStringValue.substring( 0, FIELD_LENGTH_INDICATOR_LENGTH );
        
        if ( NEG_FIELD_LENGTH_IDICATOR.equals( fli ) )
        {
            reasonCode = ReasonCode.valueOf( fieldStringValue.substring( 5, 7 ) );
            captureCode = CaptureCode.valueOf( fieldStringValue.substring( 7, 8 ) );
            recordAddDateString = fieldStringValue.substring( 8, 14 );
            recordExpirationDateString = fieldStringValue.substring( 14 );
        }
        else
        {
            cardStatus = fieldStringValue.substring( 5, 6 );
            pinOffset = fieldStringValue.substring( 6, 22 );
            expirationDateString = fieldStringValue.substring( 94, 98 );
        }
    }

    public String getCardType()
    {
        return cardType;
    }

    public void setCardType( String cardType )
    {
        this.cardType = cardType;
    }

    public String getCardStatus()
    {
        return cardStatus;
    }

    public void setCardStatus( String cardStatus )
    {
        this.cardStatus = cardStatus;
    }

    public String getPinOffset()
    {
        return pinOffset;
    }

    public void setPinOffset( String pinOffset )
    {
        this.pinOffset = pinOffset;
    }

    public String getExpirationDateString()
    {
        return expirationDateString;
    }

    public void setExpirationDateString( String expirationDateString )
    {
        this.expirationDateString = expirationDateString;
    }

    public ReasonCode getReasonCode()
    {
        return reasonCode;
    }

    public void setReasonCode( ReasonCode reasonCode )
    {
        this.reasonCode = reasonCode;
    }

    public CaptureCode getCaptureCode()
    {
        return captureCode;
    }

    public void setCaptureCode( CaptureCode captureCode )
    {
        this.captureCode = captureCode;
    }

    public String getRecordAddDateString()
    {
        return recordAddDateString;
    }

    public void setRecordAddDateString( String recordAddDateString )
    {
        this.recordAddDateString = recordAddDateString;
    }

    public String getRecordExpirationDateString()
    {
        return recordExpirationDateString;
    }

    public void setRecordExpirationDateString( String recordExpirationDateString )
    {
        this.recordExpirationDateString = recordExpirationDateString;
    }
}