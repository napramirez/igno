package com.napramirez.igno.server.message.field.atm;

/**
 * UserDataMultipleAccount - Field 127 in FIS ISO Specifications
 *
 * ATM - Multiple Account Select
 *
 * TODO: parse/extract the multiple accounts
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class UserDataMultipleAccount
{
    private static final int FIELD_LENGTH = 157;

    private String fieldLengthIndicator;

    private String accountType;

    private String numberOfAccounts;

    private String reserved;

    private String accountInformation;

    public UserDataMultipleAccount( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "User Data (ATM-Multiple Account Select) field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        fieldLengthIndicator = fieldStringValue.substring( 3, 5 );
        fieldLengthIndicator = fieldStringValue.substring( 5, 6 );
        fieldLengthIndicator = fieldStringValue.substring( 6, 7 );
        fieldLengthIndicator = fieldStringValue.substring( 7, 154 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType( String accountType )
    {
        this.accountType = accountType;
    }

    public String getNumberOfAccounts()
    {
        return numberOfAccounts;
    }

    public void setNumberOfAccounts( String numberOfAccounts )
    {
        this.numberOfAccounts = numberOfAccounts;
    }

    public String getReserved()
    {
        return reserved;
    }

    public void setReserved( String reserved )
    {
        this.reserved = reserved;
    }

    public String getAccountInformation()
    {
        return accountInformation;
    }

    public void setAccountInformation( String accountInformation )
    {
        this.accountInformation = accountInformation;
    }

    /**
     * AccountInformation - Position 8-154
     */
    public class AccountInformation
    {
        private String accountNumber;

        private String reserved;

        private String accountDescription;

        public AccountInformation( String fieldStringValue )
        {
            accountNumber = fieldStringValue.substring( 0, 19 );
            reserved = fieldStringValue.substring( 19, 20 );
            accountDescription = fieldStringValue.substring( 20 );
        }

        public String getAccountNumber()
        {
            return accountNumber;
        }

        public void setAccountNumber( String accountNumber )
        {
            this.accountNumber = accountNumber;
        }

        public String getReserved()
        {
            return reserved;
        }

        public void setReserved( String reserved )
        {
            this.reserved = reserved;
        }

        public String getAccountDescription()
        {
            return accountDescription;
        }

        public void setAccountDescription( String accountDescription )
        {
            this.accountDescription = accountDescription;
        }
    }
}
