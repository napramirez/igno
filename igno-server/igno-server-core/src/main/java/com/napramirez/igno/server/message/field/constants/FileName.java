package com.napramirez.igno.server.message.field.constants;

/**
 * File Name - Field 101 in FIS ISO Specification
 * 
 * @author ztorres
 *
 */
public enum FileName
{
    POSITIVE_BALANCE_FIEL( "CC" ),
    CARD_AUTHORIZATION_FILE( "CF" ),
    NEGATIVE_CARD_FIEL( "NF" );
    
    private String code;
    private FileName( String code )
    {
        this.code = code;
    }
    
    public String toString()
    {
        return code;
    }
}