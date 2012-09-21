package com.napramirez.igno.server.common.field;

/**
 * AccountIndicator - Field 125 in FIS ISO Specifications
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class AccountIndicator
{
    public enum Indicator
    {
        PROCESS_BOTH_TO_AND_FROM_ACCOUNTS( "0" ),
        PROCESS_ONLY_FROM_ACCOUNT( "1" ),
        PROCESS_ONLY_TO_ACCOUNT( "2" );

        private String code;

        Indicator( String code )
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

    private String accountIndicator;

    public AccountIndicator( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Account Indicator field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        accountIndicator = fieldStringValue.substring( 3 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getAccountIndicator()
    {
        return accountIndicator;
    }

    public void setAccountIndicator( String accountIndicator )
    {
        this.accountIndicator = accountIndicator;
    }
}
