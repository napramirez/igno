package com.napramirez.igno.server.message.field;

/**
 * PostalCode - Field 062 in FIS ISO Specifications
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class PostalCode
{
    private static final int FIELD_LENGTH = 4;

    private String fieldLengthIndicator;

    private String postalCode;

    public PostalCode( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "File Name field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        postalCode = fieldStringValue.substring( 3 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode( String postalCode )
    {
        this.postalCode = postalCode;
    }
}
