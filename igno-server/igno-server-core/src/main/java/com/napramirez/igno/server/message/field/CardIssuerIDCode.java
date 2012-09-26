package com.napramirez.igno.server.message.field;

/**
 * CardIssuerIDCode - Field 122 in FIS ISO Specifications
 *
 * POS
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class CardIssuerIDCode
{
    private static final int FIELD_LENGTH = 14;

    private String fieldLengthIndicator;

    private String cardIssuerId;

    public CardIssuerIDCode( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Card Issuer ID Code field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        cardIssuerId = fieldStringValue.substring( 3 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getCardIssuerId()
    {
        return cardIssuerId;
    }

    public void setCardIssuerId( String cardIssuerId )
    {
        this.cardIssuerId = cardIssuerId;
    }
}
