package com.napramirez.igno.server.message.field.atm;

/**
 * CardIssuerAndAuthorizerData - Field 061 in FIS ISO Specifications
 *
 * ATM
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class CardIssuerAndAuthorizerData
{
    /**
     * AccountIndicator - Positions 12-13, 14-15
     */
    public enum AccountIndicator
    {
        SAVINGS( "11" ),
        DDA( "01" ),
        CREDIT( "31" );

        private String code;

        private AccountIndicator( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    /**
     * Authorizer - Position 16
     */
    public enum Authorizer
    {
        PRIMARY( "P" ),
        ALTERNATE( "A" );

        private String code;

        private Authorizer( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    private static final int FIELD_LENGTH = 16;

    private String fieldLengthIndicator;

    private String cardIssuerFIID;

    private String cardLogicalNetwork;

    private String saveFromAccountIndicator;

    private String saveToAccountIndicator;

    private String authorizer;

    public CardIssuerAndAuthorizerData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Card Issuer and Authorizer Data (ATM) field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        cardIssuerFIID = fieldStringValue.substring( 3, 7 );
        cardLogicalNetwork = fieldStringValue.substring( 7, 11 );
        saveFromAccountIndicator = fieldStringValue.substring( 11, 13 );
        saveToAccountIndicator = fieldStringValue.substring( 13, 15 );
        authorizer = fieldStringValue.substring( 15 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getCardIssuerFIID()
    {
        return cardIssuerFIID;
    }

    public void setCardIssuerFIID( String cardIssuerFIID )
    {
        this.cardIssuerFIID = cardIssuerFIID;
    }

    public String getCardLogicalNetwork()
    {
        return cardLogicalNetwork;
    }

    public void setCardLogicalNetwork( String cardLogicalNetwork )
    {
        this.cardLogicalNetwork = cardLogicalNetwork;
    }

    public String getSaveFromAccountIndicator()
    {
        return saveFromAccountIndicator;
    }

    public void setSaveFromAccountIndicator( String saveFromAccountIndicator )
    {
        this.saveFromAccountIndicator = saveFromAccountIndicator;
    }

    public String getSaveToAccountIndicator()
    {
        return saveToAccountIndicator;
    }

    public void setSaveToAccountIndicator( String saveToAccountIndicator )
    {
        this.saveToAccountIndicator = saveToAccountIndicator;
    }

    public String getAuthorizer()
    {
        return authorizer;
    }

    public void setAuthorizer( String authorizer )
    {
        this.authorizer = authorizer;
    }
}
