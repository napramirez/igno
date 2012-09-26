package com.napramirez.igno.server.message.field.atm;

/**
 * DepositCreditAmount - Field 123 in FIS ISO Specifications
 *
 * ATM
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class DepositCreditAmount
{
    private static final int FIELD_LENGTH = 15;

    private String fieldLengthIndicator;

    private String depositCreditAmount;

    public DepositCreditAmount( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Deposit Credit Amount field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        depositCreditAmount = fieldStringValue.substring( 3 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getDepositCreditAmount()
    {
        return depositCreditAmount;
    }

    public void setDepositCreditAmount( String depositCreditAmount )
    {
        this.depositCreditAmount = depositCreditAmount;
    }
}
