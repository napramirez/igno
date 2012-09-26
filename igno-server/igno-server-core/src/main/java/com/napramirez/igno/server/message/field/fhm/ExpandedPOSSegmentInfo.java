package com.napramirez.igno.server.message.field.fhm;

/**
 * ExpandedPOSSegmentInfo - Field 122 in FIS ISO Specifications
 *
 * File Update (CAF)
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class ExpandedPOSSegmentInfo
{
    private static final int FIELD_LENGTH = 120;

    private String fieldLengthIndicator;

    private String reserved;

    public ExpandedPOSSegmentInfo( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Expanded POS Segment Information field is invalid!" );
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
