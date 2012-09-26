package com.napramirez.igno.server.message.field.atm;

/**
 * UserDataBalanceInquiry - Field 127 in FIS ISO Specifications
 *
 * ATM - Balance Inquiry Link Usage
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class UserDataBalanceInquiry
{
    public enum TransactionTypeIndicator
    {
        ATM_ORIGINATED_TRANSACTION( "A" ),
        POS_ORIGINATED_TRANSACTION( "P" );

        private String code;

        private TransactionTypeIndicator( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    private static final int FIELD_LENGTH = 60;

    private String reserved;

    private String originalTransactionAmount;

    private String originalTransactionTypeIndicator;

    private String originalTransactionCode;

    public UserDataBalanceInquiry( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "User Data (ATM-Balance Inquiry Link Usage) field is invalid!" );
        }

        reserved = fieldStringValue.substring( 0, 49 );
        originalTransactionAmount = fieldStringValue.substring( 49, 57 );
        originalTransactionTypeIndicator = fieldStringValue.substring( 57, 58 );
        originalTransactionCode = fieldStringValue.substring( 58 );
    }

    public String getReserved()
    {
        return reserved;
    }

    public void setReserved( String reserved )
    {
        this.reserved = reserved;
    }

    public String getOriginalTransactionAmount()
    {
        return originalTransactionAmount;
    }

    public void setOriginalTransactionAmount( String originalTransactionAmount )
    {
        this.originalTransactionAmount = originalTransactionAmount;
    }

    public String getOriginalTransactionTypeIndicator()
    {
        return originalTransactionTypeIndicator;
    }

    public void setOriginalTransactionTypeIndicator( String originalTransactionTypeIndicator )
    {
        this.originalTransactionTypeIndicator = originalTransactionTypeIndicator;
    }

    public String getOriginalTransactionCode()
    {
        return originalTransactionCode;
    }

    public void setOriginalTransactionCode( String originalTransactionCode )
    {
        this.originalTransactionCode = originalTransactionCode;
    }
}
