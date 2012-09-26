package com.napramirez.igno.server.message.field.netman;

/**
 * SecurityRelatedControlInfo - Field 053 in FIS ISO Specifications
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class SecurityRelatedControlInfo
{
    public enum KeyType
    {
        PIN_KEY( "00" ),
        MAC_KEY( "01" );

        private String code;

        private KeyType( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    public enum KeyDirection
    {
        OUTBOUND_KEY_ONLY( "01" ),
        INBOUND_KEY_ONLY( "02" ),
        INBOUND_AND_OUTBOUND_KEYS( "03" );

        private String code;

        private KeyDirection( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    private static final int FIELD_LENGTH = 16;

    private String keyType;

    private String keyDirection;

    private String reserved;

    public SecurityRelatedControlInfo( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Security Related Control Information field is invalid!" );
        }

        keyType = fieldStringValue.substring( 0, 2 );
        keyDirection = fieldStringValue.substring( 2, 4 );
        reserved = fieldStringValue.substring( 4 );
    }

    public String getKeyType()
    {
        return keyType;
    }

    public void setKeyType( String keyType )
    {
        this.keyType = keyType;
    }

    public String getKeyDirection()
    {
        return keyDirection;
    }

    public void setKeyDirection( String keyDirection )
    {
        this.keyDirection = keyDirection;
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
