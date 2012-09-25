package com.napramirez.igno.server.common.field;

/**
 * AdditionalAmounts - Field 054 in FIS ISO Specifications
 *
 * POS
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class AdditionalAmounts
{
    private static final int FIELD_LENGTH = 15;

    private String fieldLengthIndicator;

    private String cashbackAmount;

    public AdditionalAmounts( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Additional Amounts field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        cashbackAmount = fieldStringValue.substring( 3 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getCashbackAmount()
    {
        return cashbackAmount;
    }

    public void setCashbackAmount( String cashbackAmount )
    {
        this.cashbackAmount = cashbackAmount;
    }
}
