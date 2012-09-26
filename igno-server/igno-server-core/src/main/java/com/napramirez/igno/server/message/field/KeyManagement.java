package com.napramirez.igno.server.message.field;

/**
 * KeyManagement - Field 120 in FIS ISO Specifications
 *
 * Network Management
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class KeyManagement
{
    private static final int FIELD_LENGTH = 9;

    private String fieldLengthIndicator;

    private String checkDigits;

    public KeyManagement( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Key Management field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        checkDigits = fieldStringValue.substring( 3 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getCheckDigits()
    {
        return checkDigits;
    }

    public void setCheckDigits( String checkDigits )
    {
        this.checkDigits = checkDigits;
    }
}
