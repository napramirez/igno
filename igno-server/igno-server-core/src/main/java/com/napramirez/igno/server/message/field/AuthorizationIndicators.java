package com.napramirez.igno.server.message.field;

/**
 * AuthorizationIndicators - Field 121 in FIS ISO Specifications
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class AuthorizationIndicators
{
    /**
     * AuthorizationIndicator - Position 22
     */
    public enum AuthorizationIndicator
    {
        PRIMARY_AUTHORIZER( "P" ),
        FIRST_ALTERNATE_AUTHORIZER( "1" ),
        SECOND_ALTERNATE_AUTHORIZER( "2" ),
        DEFAULT_AUTHORIZER( "4" ),
        NO_AUTHORIZER_DETERMINED_YET( "9" ),
        SPROUTE_PRIMARY_AUTHORIZER( "F" ),
        SPROUTE_ALTERNATE_AUTHORIZER( "f" );

        private String code;

        AuthorizationIndicator( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    private static final int FIELD_LENGTH = 23;

    private String fieldLengthIndicator;

    private String clerkId;

    private String crtAuthorizationGroup;

    private String crtAuthorizationUserId;

    private String authorizationIndicator;

    public AuthorizationIndicators( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Authorization Indicators field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        clerkId = fieldStringValue.substring( 3, 9 );
        crtAuthorizationGroup = fieldStringValue.substring( 9, 13 );
        crtAuthorizationUserId = fieldStringValue.substring( 13, 21 );
        authorizationIndicator = fieldStringValue.substring( 21 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getClerkId()
    {
        return clerkId;
    }

    public void setClerkId( String clerkId )
    {
        this.clerkId = clerkId;
    }

    public String getCrtAuthorizationGroup()
    {
        return crtAuthorizationGroup;
    }

    public void setCrtAuthorizationGroup( String crtAuthorizationGroup )
    {
        this.crtAuthorizationGroup = crtAuthorizationGroup;
    }

    public String getCrtAuthorizationUserId()
    {
        return crtAuthorizationUserId;
    }

    public void setCrtAuthorizationUserId( String crtAuthorizationUserId )
    {
        this.crtAuthorizationUserId = crtAuthorizationUserId;
    }

    public String getAuthorizationIndicator()
    {
        return authorizationIndicator;
    }

    public void setAuthorizationIndicator( String authorizationIndicator )
    {
        this.authorizationIndicator = authorizationIndicator;
    }
}
