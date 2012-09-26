package com.napramirez.igno.server.message.field.constants;

/**
 * NetworkManagementInformationCode
 * 
 * @author <a href="mailto:napramirez@gmail.com"></a>
 */
public enum NetworkManagementInformationCode
{
    LOGON( "001" ),
    LOGOFF( "002" ),
    CHANGE_KEY( "161" ),
    NEW_KEY( "162" ),
    REPEAT_KEY( "163" ),
    VERIFY_KEY( "164" ),
    ECHO_TEST( "301" );

    private String code;

    private NetworkManagementInformationCode( String code )
    {
        this.code = code;
    }

    public String toString()
    {
        return code;
    }
}
