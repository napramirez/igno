package com.napramirez.igno.server.message.field;

/**
 * AdditionalData - Field 063 in FIS ISO Specifications
 *
 * POS
 *
 * TODO: Implement the Token Data
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class POSAdditionalData
{
    private static final int FIELD_LENGTH = 600;

    private String fieldLengthIndicator;

    private String headerToken;
    
    private String tokenHeadersAndTokenData;
    
    public POSAdditionalData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Additional Data field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        headerToken = fieldStringValue.substring( 3, 15 );
        tokenHeadersAndTokenData = fieldStringValue.substring( 15 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getHeaderToken()
    {
        return headerToken;
    }

    public void setHeaderToken( String headerToken )
    {
        this.headerToken = headerToken;
    }

    public String getTokenHeadersAndTokenData()
    {
        return tokenHeadersAndTokenData;
    }

    public void setTokenHeadersAndTokenData( String tokenHeadersAndTokenData )
    {
        this.tokenHeadersAndTokenData = tokenHeadersAndTokenData;
    }
}
