package com.napramirez.igno.server.message.field.pos;

/**
 * PreauthorizationAndChargebackData - Field 126 in FIS ISO Specifications
 *
 * POS
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class PreauthorizationAndChargebackData
{
    /**
     * PreauthorizationHoldInterval - Position 4
     */
    public enum PreauthorizationHoldInterval
    {
        MINUTES( "0" ),
        HOURS( "1" ),
        DAYS( "2" );

        private String code;

        PreauthorizationHoldInterval( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    private static final int FIELD_LENGTH = 41;

    private String fieldLengthIndicator;

    private String preauthorizationHoldInterval;

    private String preauthorizationHoldDuration;

    private String preauthorizationSequenceNumber;

    private String referralPhoneNumberOrMastercardAdviceReasonCode;

    private String chargebackReason;

    private String chargebackNumber;

    public PreauthorizationAndChargebackData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Preauthorization and Chargeback Data field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        preauthorizationHoldInterval = fieldStringValue.substring( 3, 4 );
        preauthorizationHoldDuration = fieldStringValue.substring( 4, 6 );
        preauthorizationSequenceNumber = fieldStringValue.substring( 6, 18 );
        referralPhoneNumberOrMastercardAdviceReasonCode = fieldStringValue.substring( 18, 38 );
        chargebackReason = fieldStringValue.substring( 38, 40 );
        chargebackNumber = fieldStringValue.substring( 40 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getPreauthorizationHoldInterval()
    {
        return preauthorizationHoldInterval;
    }

    public void setPreauthorizationHoldInterval( String preauthorizationHoldInterval )
    {
        this.preauthorizationHoldInterval = preauthorizationHoldInterval;
    }

    public String getPreauthorizationHoldDuration()
    {
        return preauthorizationHoldDuration;
    }

    public void setPreauthorizationHoldDuration( String preauthorizationHoldDuration )
    {
        this.preauthorizationHoldDuration = preauthorizationHoldDuration;
    }

    public String getPreauthorizationSequenceNumber()
    {
        return preauthorizationSequenceNumber;
    }

    public void setPreauthorizationSequenceNumber( String preauthorizationSequenceNumber )
    {
        this.preauthorizationSequenceNumber = preauthorizationSequenceNumber;
    }

    public String getReferralPhoneNumberOrMastercardAdviceReasonCode()
    {
        return referralPhoneNumberOrMastercardAdviceReasonCode;
    }

    public void setReferralPhoneNumberOrMastercardAdviceReasonCode( String referralPhoneNumberOrMastercardAdviceReasonCode )
    {
        this.referralPhoneNumberOrMastercardAdviceReasonCode = referralPhoneNumberOrMastercardAdviceReasonCode;
    }

    public String getChargebackReason()
    {
        return chargebackReason;
    }

    public void setChargebackReason( String chargebackReason )
    {
        this.chargebackReason = chargebackReason;
    }

    public String getChargebackNumber()
    {
        return chargebackNumber;
    }

    public void setChargebackNumber( String chargebackNumber )
    {
        this.chargebackNumber = chargebackNumber;
    }
}
