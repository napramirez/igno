package com.napramirez.igno.server.message.field.fhm;

/**
 * ExpandedATMSegmentInfo - Field 121 in FIS ISO Specifications
 *
 * File Update (CAF)
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class ExpandedATMSegmentInfo
{
    private static final int FIELD_LENGTH = 79;

    private String fieldLengthIndicator;

    private String reserved;

    public ExpandedATMSegmentInfo( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Expanded ATM Segment Information field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        reserved = fieldStringValue.substring( 3 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getReserved()
    {
        return reserved;
    }

    public void setReserved( String reserved )
    {
        this.reserved = reserved;
    }
}
