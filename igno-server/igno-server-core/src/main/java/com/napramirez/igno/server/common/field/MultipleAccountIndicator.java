package com.napramirez.igno.server.common.field;

/**
 * MultipleAccountIndicator - Field 122 in FIS ISO Specifications
 *
 * ATM
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class MultipleAccountIndicator
{
    public enum Indicator
    {
        MULTIPLE_ACCOUNT_SELECTION_NOT_ALLOWED( "0" ),
        MULTIPLE_ACCOUNT_SELECTION_ALLOWED( "1" ),
        FAST_CASH( "2" );

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

    private static final int FIELD_LENGTH = 14;

    private String fieldLengthIndicator;

    private String multipleAccountIndicator;

    private String blank;

    public MultipleAccountIndicator( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Multiple Account Indicator field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        multipleAccountIndicator = fieldStringValue.substring( 3, 4 );
        blank = fieldStringValue.substring( 4 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getMultipleAccountIndicator()
    {
        return multipleAccountIndicator;
    }

    public void setMultipleAccountIndicator( String multipleAccountIndicator )
    {
        this.multipleAccountIndicator = multipleAccountIndicator;
    }

    public String getBlank()
    {
        return blank;
    }

    public void setBlank( String blank )
    {
        this.blank = blank;
    }
}
