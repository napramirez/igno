package com.napramirez.igno.server.message.field;

/**
 * CardAcceptorIdentificationCode - Field 042 in FIS ISO Specifications
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class CardAcceptorIdentificationCode
{
    public enum TerminalServiceIndicator
    {
        NATIONAL_TRANSACTION( "N" ),
        FOREIGN_TRANSACTION( "F" ),
        LOCAL_FASTBANK_TRANSACTION( "L" ),
        LOCAL_BAYBANK_TRANSACTION( "Y" ),
        HOME_BANKING_TRANSACTION( "H" );

        private String code;

        TerminalServiceIndicator( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    public enum PreauthIndicator
    {
        PREAUTHORIZATION_TRANSACTION( "P" );

        private String code;

        PreauthIndicator( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    public enum CVVFailureIndicator
    {
        CVV_FAILURE( "F" );

        private String code;

        CVVFailureIndicator( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    public enum PreauthAmountIndicator
    {
        PREAUTHORIZATION_TRANSACTION( "P" );

        private String code;

        PreauthAmountIndicator( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    public enum DebitPadCheckIndicator
    {
        TERMINAL_SUPPORTS_OVERDRAFT_WARNING( "W" ),
        TERMINAL_SUPPORTS_OVERDRAFT_WARNING2( "X" ),
        DISPLAY_OVERDRAFT_WARNING( "Z" );

        private String code;

        DebitPadCheckIndicator( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    public enum ISAFee
    {
        VISA_MULTI_CURRENCY_ISAFEE_ASSESSED( "1" ),
        VISA_SINGLE_CURRENCY_ISAFEE_ASSESSED( "2" ),
        MASTERCARD_CROSSBORDER_TRANSACTION_FLAG( "Y" ),
        ISAFEE_NOT_ASSESSED( "0" ),
        ISAFEE_NOT_ASSESSED2( " " );

        private String code;

        ISAFee( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    public enum MastercardCrossBorderCurrencyFlag
    {
        YES( "Y" ),
        NO( "N" ),
        NOT_CROSS_BORDER( "0" ),
        NOT_CROSS_BORDER2( " " );

        private String code;

        MastercardCrossBorderCurrencyFlag( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    private static final int FIELD_LENGTH = 15;

    private String echo;

    private String terminalServiceIndicator;

    private String preauthIndicator;

    private String cvvFailureIndicator;

    private String blank;

    private String recurringPaymentFlag;

    private String echo2;

    private String preauthAmountIndicator;

    public CardAcceptorIdentificationCode( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Card Acceptor Identification Code field is invalid!" );
        }

        echo = fieldStringValue.substring( 0, 1 );
        terminalServiceIndicator = fieldStringValue.substring( 1, 2 );
        preauthIndicator = fieldStringValue.substring( 2, 3 );
        cvvFailureIndicator = fieldStringValue.substring( 3, 4 );
        blank = fieldStringValue.substring( 4, 5 );
        recurringPaymentFlag = fieldStringValue.substring( 5, 6 );
        echo2 = fieldStringValue.substring( 6, 9 );
        preauthAmountIndicator = fieldStringValue.substring( 9 );
    }

    public String getEcho()
    {
        return echo;
    }

    public void setEcho( String echo )
    {
        this.echo = echo;
    }

    public String getTerminalServiceIndicator()
    {
        return terminalServiceIndicator;
    }

    public void setTerminalServiceIndicator( String terminalServiceIndicator )
    {
        this.terminalServiceIndicator = terminalServiceIndicator;
    }

    public String getPreauthIndicator()
    {
        return preauthIndicator;
    }

    public void setPreauthIndicator( String preauthIndicator )
    {
        this.preauthIndicator = preauthIndicator;
    }

    public String getCvvFailureIndicator()
    {
        return cvvFailureIndicator;
    }

    public void setCvvFailureIndicator( String cvvFailureIndicator )
    {
        this.cvvFailureIndicator = cvvFailureIndicator;
    }

    public String getBlank()
    {
        return blank;
    }

    public void setBlank( String blank )
    {
        this.blank = blank;
    }

    public String getRecurringPaymentFlag()
    {
        return recurringPaymentFlag;
    }

    public void setRecurringPaymentFlag( String recurringPaymentFlag )
    {
        this.recurringPaymentFlag = recurringPaymentFlag;
    }

    public String getEcho2()
    {
        return echo2;
    }

    public void setEcho2( String echo2 )
    {
        this.echo2 = echo2;
    }

    public String getPreauthAmountIndicator()
    {
        return preauthAmountIndicator;
    }

    public void setPreauthAmountIndicator( String preauthAmountIndicator )
    {
        this.preauthAmountIndicator = preauthAmountIndicator;
    }
}
