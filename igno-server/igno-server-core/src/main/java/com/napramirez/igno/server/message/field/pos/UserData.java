package com.napramirez.igno.server.message.field.pos;

/**
 * UserData - Field 127 in FIS ISO Specifications
 *
 * POS
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class UserData
{
    public enum POSBalanceUsageIndicator
    {
        NO_BALANCES( "0" ),
        LEDGER_BALANCE_ONLY( "1" ),
        AVAILABLE_BALANCE_ONLY( "2" ),
        BOTH_LEDGER_AND_AVAILABLE_BALANCES( "3" );

        private String code;

        private POSBalanceUsageIndicator( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    private static final int FIELD_LENGTH = 60;

    private String ps2000TransactionId;

    private String ps200ServiceIndicator;

    private String surchargeAmount;

    private String visaCVV2Result;

    private String visaCVV2PresenceIndicator;

    private String visaCVV2Value;

    private String posBalanceUsageIndicator;

    private String firstBalanceSign;

    private String firstBalance;

    private String secondBalanceSign;

    private String secondBalance;

    private String filler;

    public UserData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "User Data (POS) field is invalid!" );
        }

        ps2000TransactionId = fieldStringValue.substring( 0, 15 );
        ps200ServiceIndicator = fieldStringValue.substring( 15, 16 );
        surchargeAmount = fieldStringValue.substring( 16, 28 );
        visaCVV2Result = fieldStringValue.substring( 28, 29 );
        visaCVV2PresenceIndicator = fieldStringValue.substring( 29, 30 );
        visaCVV2Value = fieldStringValue.substring( 30, 34 );
        posBalanceUsageIndicator = fieldStringValue.substring( 34, 35 );
        firstBalanceSign = fieldStringValue.substring( 35, 36 );
        firstBalance = fieldStringValue.substring( 36, 47 );
        secondBalanceSign = fieldStringValue.substring( 47, 48 );
        secondBalance = fieldStringValue.substring( 48, 59 );
        filler = fieldStringValue.substring( 59 );
    }

    public String getPs2000TransactionId()
    {
        return ps2000TransactionId;
    }

    public void setPs2000TransactionId( String ps2000TransactionId )
    {
        this.ps2000TransactionId = ps2000TransactionId;
    }

    public String getPs200ServiceIndicator()
    {
        return ps200ServiceIndicator;
    }

    public void setPs200ServiceIndicator( String ps200ServiceIndicator )
    {
        this.ps200ServiceIndicator = ps200ServiceIndicator;
    }

    public String getSurchargeAmount()
    {
        return surchargeAmount;
    }

    public void setSurchargeAmount( String surchargeAmount )
    {
        this.surchargeAmount = surchargeAmount;
    }

    public String getVisaCVV2Result()
    {
        return visaCVV2Result;
    }

    public void setVisaCVV2Result( String visaCVV2Result )
    {
        this.visaCVV2Result = visaCVV2Result;
    }

    public String getVisaCVV2PresenceIndicator()
    {
        return visaCVV2PresenceIndicator;
    }

    public void setVisaCVV2PresenceIndicator( String visaCVV2PresenceIndicator )
    {
        this.visaCVV2PresenceIndicator = visaCVV2PresenceIndicator;
    }

    public String getVisaCVV2Value()
    {
        return visaCVV2Value;
    }

    public void setVisaCVV2Value( String visaCVV2Value )
    {
        this.visaCVV2Value = visaCVV2Value;
    }

    public String getPosBalanceUsageIndicator()
    {
        return posBalanceUsageIndicator;
    }

    public void setPosBalanceUsageIndicator( String posBalanceUsageIndicator )
    {
        this.posBalanceUsageIndicator = posBalanceUsageIndicator;
    }

    public String getFirstBalanceSign()
    {
        return firstBalanceSign;
    }

    public void setFirstBalanceSign( String firstBalanceSign )
    {
        this.firstBalanceSign = firstBalanceSign;
    }

    public String getFirstBalance()
    {
        return firstBalance;
    }

    public void setFirstBalance( String firstBalance )
    {
        this.firstBalance = firstBalance;
    }

    public String getSecondBalanceSign()
    {
        return secondBalanceSign;
    }

    public void setSecondBalanceSign( String secondBalanceSign )
    {
        this.secondBalanceSign = secondBalanceSign;
    }

    public String getSecondBalance()
    {
        return secondBalance;
    }

    public void setSecondBalance( String secondBalance )
    {
        this.secondBalance = secondBalance;
    }

    public String getFiller()
    {
        return filler;
    }

    public void setFiller( String filler )
    {
        this.filler = filler;
    }
}
