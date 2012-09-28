package com.napramirez.igno.server.message.field.validation;

/**
 * FieldDataType - defines the format (in Regular Expressions) of the data element types of the FIS ISO8583
 * specifications
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public enum FieldType
{
    ALPHABETIC( "^[a-zA-Z]*$" ),
    NUMERIC( "^[0-9]*$" ),
    ALPHA_NUMERIC( "^[a-zA-Z0-9]*$" ),
    ALPHA_NUMERIC_SPECIAL( "^[a-zA-Z0-9!@#$%^&*()\\-_=+\\[\\]\\;:'\",./?\\s]*$" ),
    AMOUNT( "^[CD\\-\\+\\s][0-9]{8}$" );

    private String regex;

    FieldType( String regex )
    {
        this.regex = regex;
    }

    public String toString()
    {
        return regex;
    }
}
