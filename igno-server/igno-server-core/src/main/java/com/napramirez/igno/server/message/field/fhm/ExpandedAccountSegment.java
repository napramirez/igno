package com.napramirez.igno.server.message.field.fhm;

/**
 * ExpandedAccountSegment - Field 126 in FIS ISO Specifications
 *
 * File Update (CAF)
 *
 * TODO: parse/extract the multiple accounts
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class ExpandedAccountSegment
{
    private static final int FIELD_LENGTH = 693;

    private String fieldLengthIndicator;

    private String accountCount;

    private String accountInformation;

    public ExpandedAccountSegment( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Expanded Account Segment field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        accountCount = fieldStringValue.substring( 3, 5 );
        accountInformation = fieldStringValue.substring( 5 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getAccountCount()
    {
        return accountCount;
    }

    public void setAccountCount( String accountCount )
    {
        this.accountCount = accountCount;
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
     * AccountInformation - Position 6-693
     */
    public class AccountInformation
    {
        private String accountType;

        private String accountNumber;

        private String accountStatus;

        private String accountDescription;

        private String filler;

        private String accountQualifier;

        public AccountInformation( String fieldStringValue )
        {
            accountType = fieldStringValue.substring( 0, 2 );
            accountNumber = fieldStringValue.substring( 2, 30 );
            accountStatus = fieldStringValue.substring( 30, 31 );
            accountDescription = fieldStringValue.substring( 31, 41 );
            filler = fieldStringValue.substring( 41, 42 );
            accountQualifier = fieldStringValue.substring( 42 );
        }

        public String getAccountType()
        {
            return accountType;
        }

        public void setAccountType( String accountType )
        {
            this.accountType = accountType;
        }

        public String getAccountNumber()
        {
            return accountNumber;
        }

        public void setAccountNumber( String accountNumber )
        {
            this.accountNumber = accountNumber;
        }

        public String getAccountStatus()
        {
            return accountStatus;
        }

        public void setAccountStatus( String accountStatus )
        {
            this.accountStatus = accountStatus;
        }

        public String getAccountDescription()
        {
            return accountDescription;
        }

        public void setAccountDescription( String accountDescription )
        {
            this.accountDescription = accountDescription;
        }

        public String getFiller()
        {
            return filler;
        }

        public void setFiller( String filler )
        {
            this.filler = filler;
        }

        public String getAccountQualifier()
        {
            return accountQualifier;
        }

        public void setAccountQualifier( String accountQualifier )
        {
            this.accountQualifier = accountQualifier;
        }
    }
}
