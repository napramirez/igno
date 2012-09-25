package com.napramirez.igno.server.common.field;

/**
 * ATMUserData - Field 127 in FIS ISO Specifications
 *
 * ATM
 *
 * TODO: Implement subclasses for this field
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class ATMUserData
{
    private static final int FIELD_LENGTH = 200;

    private String fieldLengthIndicator;

    private String content;

    public ATMUserData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "User Data (ATM) field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        content = fieldStringValue.substring( 3 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent( String content )
    {
        this.content = content;
    }
}
