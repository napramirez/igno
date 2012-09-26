package com.napramirez.igno.server.common.field;

/**
 * FHM Additional Data - Field 48 in FIS ISO Specification
 * 
 * @author ztorres
 *
 */
public class FHMAdditionalData
{
    private static final int FIELD_LENGTH = 79;
    private String memberNumber;         // 4-6
    private String cardFIID;             // 7-10
    
    public FHMAdditionalData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "FHM Additional Data is invalid" );
        }
        
        memberNumber = fieldStringValue.substring( 3, 6 );
        cardFIID = fieldStringValue.substring( 6 );
    }
    
    public String getMemberNumber()
    {
        return memberNumber;
    }
    public void setMemberNumber( String memberNumber )
    {
        this.memberNumber = memberNumber;
    }
    public String getCardFIID()
    {
        return cardFIID;
    }
    public void setCardFIID( String cardFIID )
    {
        this.cardFIID = cardFIID;
    }
}