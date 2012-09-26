package com.napramirez.igno.server.message.field;

/**
 * ExpandedBaseSegment - Field 120 in FIS ISO Specifications
 * 
 * File Update (CAF)
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class ExpandedBaseSegment
{
    private static final int FIELD_LENGTH = 98;

    private String fieldLengthIndicator;

    private String careType;

    private String cardStatus;

    private String pinOffset;

    private String reserved;

    private String expirationDate;

    private String reserved2;

    public ExpandedBaseSegment( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Expanded Base Segment field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        careType = fieldStringValue.substring( 3, 5 );
        cardStatus = fieldStringValue.substring( 5, 6 );
        pinOffset = fieldStringValue.substring( 6, 22 );
        reserved = fieldStringValue.substring( 22, 94 );
        expirationDate = fieldStringValue.substring( 94, 98 );
        reserved2 = fieldStringValue.substring( 98 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getCareType()
    {
        return careType;
    }

    public void setCareType( String careType )
    {
        this.careType = careType;
    }

    public String getCardStatus()
    {
        return cardStatus;
    }

    public void setCardStatus( String cardStatus )
    {
        this.cardStatus = cardStatus;
    }

    public String getPinOffset()
    {
        return pinOffset;
    }

    public void setPinOffset( String pinOffset )
    {
        this.pinOffset = pinOffset;
    }

    public String getReserved()
    {
        return reserved;
    }

    public void setReserved( String reserved )
    {
        this.reserved = reserved;
    }

    public String getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate( String expirationDate )
    {
        this.expirationDate = expirationDate;
    }

    public String getReserved2()
    {
        return reserved2;
    }

    public void setReserved2( String reserved2 )
    {
        this.reserved2 = reserved2;
    }
}
