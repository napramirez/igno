package com.napramirez.igno.server.common.constants;

/**
 * FileUpdateCode - Field 091 in FIS ISO Specifications
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public enum FileUpdateCode
{
    ADD_RECORD( "1" ),
    REPLACE_RECORD( "2" ),
    DELETE_RECORD( "3" ),
    INQUIRY_RECORD( "5" ),
    INCREMENT_RECORD( "9" );

    private String code;

    private FileUpdateCode( String code )
    {
        this.code = code;
    }

    public String toString()
    {
        return code;
    }
}
