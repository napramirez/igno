package com.napramirez.igno.server.message.field;

/**
 * POS Issuer-Category-Response Code Data - Field 61 in FIS ISO Specification
 * 
 * @author ztorres
 *
 */
public class POSIssuerCategoryResponseCodeData
{
    private static final int FIELD_LENGTH = 22;
    private String cardIssuerFIID;         // 4-7
    private String cardLogicalNetwork;     // 8-11
    private String category;               // 12
    private String accountIndicator;       // 13-14
    private String interchageResponseCode; // 15-22
    
    public POSIssuerCategoryResponseCodeData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "POS Issuer-Category-Respose Code Data is invalid" );
        }
        
        cardIssuerFIID = fieldStringValue.substring( 3, 7 );
        cardLogicalNetwork = fieldStringValue.substring( 7, 11 );
        category = fieldStringValue.substring( 11, 12 );
        accountIndicator = fieldStringValue.substring( 12, 14 );
        interchageResponseCode = fieldStringValue.substring( 14 );
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

    public String getCategory()
    {
        return category;
    }

    public void setCategory( String category )
    {
        this.category = category;
    }

    public String getAccountIndicator()
    {
        return accountIndicator;
    }

    public void setAccountIndicator( String accountIndicator )
    {
        this.accountIndicator = accountIndicator;
    }

    public String getInterchageResponseCode()
    {
        return interchageResponseCode;
    }

    public void setInterchageResponseCode( String interchageResponseCode )
    {
        this.interchageResponseCode = interchageResponseCode;
    }
}