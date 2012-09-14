package com.napramirez.igno.server.common.constants;

/**
 * POSConditionCode - Field 025 in FIS ISO Specifications
 *
 * @author <a href="mailto:napramirez@gmail.com"></a>
 */
public enum POSConditionCode
{
    NORMAL_TRANSACTION( "00" ),
    CUSTOMER_NOT_PRESENT( "01" ),
    UNATTENDED_CUSTOMER_OPERATED_TERMINAL( "02" ),
    MERCHANT_SUSPICIOUS_OF_TRANSACTION_OR_CARD( "03" ),
    CUSTOMER_PRESENT_CARD_NOT_PRESENT( "05" ),
    PREAUTHORIZATION_REQUEST( "06" ),
    MAIL_PHONE_ORDER( "08" ),
    CUSTOMER_IDENTITY_VERIFIED( "10" ),
    SUSPECTED_FRAUD( "11" ),
    SECURITY( "12" ),
    REPRESENTMENT_OF_ITEM( "13" ),
    ELECTRONIC_ORDER( "15" ),
    CHARGEBACK( "17" ),
    ADDRESS_ACCOUNT_VERIFICATION( "51" ),
    POS_CHECK_ORIGINAL_FULL_FINANCIAL_TRANSACTION( "52" ),
    ELECTRON_COMMERCE( "59" ),
    CARD_PRESENT_MAGNETIC_STRIPE_CANNOT_BE_READ( "71" );

    private String code;

    POSConditionCode( String code )
    {
        this.code = code;
    }

    public String toString()
    {
        return code;
    }
}
