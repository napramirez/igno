package com.napramirez.igno.server.message.field;

/**
 * ReplacementAmounts - Field 095 in FIS ISO Specifications
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class ReplacementAmounts
{
    private static final int FIELD_LENGTH = 30;

    private String actualTransactionAmount;

    private String blank;

    private String transactionFee;

    private String blank2;

    public ReplacementAmounts( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Replacement Amounts field is invalid!" );
        }

        actualTransactionAmount = fieldStringValue.substring( 0, 12 );
        blank = fieldStringValue.substring( 12, 24 );
        transactionFee = fieldStringValue.substring( 24, 33 );
        blank2 = fieldStringValue.substring( 33 );
    }

    public String getActualTransactionAmount()
    {
        return actualTransactionAmount;
    }

    public void setActualTransactionAmount( String actualTransactionAmount )
    {
        this.actualTransactionAmount = actualTransactionAmount;
    }

    public String getBlank()
    {
        return blank;
    }

    public void setBlank( String blank )
    {
        this.blank = blank;
    }

    public String getTransactionFee()
    {
        return transactionFee;
    }

    public void setTransactionFee( String transactionFee )
    {
        this.transactionFee = transactionFee;
    }

    public String getBlank2()
    {
        return blank2;
    }

    public void setBlank2( String blank2 )
    {
        this.blank2 = blank2;
    }
}
