package com.napramirez.igno.server.message.field.atm;

/**
 * PinOffset - Field 063 in FIS ISO Specifications
 *
 * ATM
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class PinOffset
{
    private static final int FIELD_LENGTH = 19;

    private String fieldLengthIndicator;

    private String pinOffset;

    public PinOffset( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Pin Offset field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        pinOffset = fieldStringValue.substring( 3 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getPinOffset()
    {
        return pinOffset;
    }

    public void setPinOffset( String pinOffset )
    {
        this.pinOffset = pinOffset;
    }
}
