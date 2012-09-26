package com.napramirez.igno.server.message.field;

/**
 * AccountIdentification1 - Field 102 in FIS ISO Specifications
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class AccountIdentification
{
    private static final int FIELD_LENGTH = 30;

    private String fieldLengthIndicator;

    private String accountNumber;

    public AccountIdentification( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Additional Data field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        accountNumber = fieldStringValue.substring( 3 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber( String accountNumber )
    {
        this.accountNumber = accountNumber;
    }
}
