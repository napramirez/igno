package com.napramirez.igno.server.message.field.atm;

/**
 * AdditionalData - Field 048 in FIS ISO Specifications
 *
 * ATM
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class AdditionalData
{
    public enum TerminalTransactionAllowedCode
    {
        NOT_ALLOWED_IF_ON_US( "0" ),
        ALLOWED_WITHIN_COUNTRY( "1" ),
        ALLOWED_WITHIN_STATE( "2" ),
        ALLOWED_NATIONALLY( "3" ),
        ALLOWED_INTERNATIONALLY( "4" );

        private String code;

        private TerminalTransactionAllowedCode( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    private static final int FIELD_LENGTH = 47;

    private String fieldLengthIndicator;

    private String sharingGroups;

    private String terminalTransactionAllowedCode;

    private String ansiTerminalStateCode;

    private String ansiTerminalCountyCode;

    private String ansiTerminalCountryCode;

    private String terminalRoutingGroup;

    public AdditionalData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Additional Data (ATM) field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        sharingGroups = fieldStringValue.substring( 3, 27 );
        terminalTransactionAllowedCode = fieldStringValue.substring( 27, 28 );
        ansiTerminalStateCode = fieldStringValue.substring( 28, 30 );
        ansiTerminalCountyCode = fieldStringValue.substring( 30, 33 );
        ansiTerminalCountryCode = fieldStringValue.substring( 33, 36 );
        terminalRoutingGroup = fieldStringValue.substring( 36 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getSharingGroups()
    {
        return sharingGroups;
    }

    public void setSharingGroups( String sharingGroups )
    {
        this.sharingGroups = sharingGroups;
    }

    public String getTerminalTransactionAllowedCode()
    {
        return terminalTransactionAllowedCode;
    }

    public void setTerminalTransactionAllowedCode( String terminalTransactionAllowedCode )
    {
        this.terminalTransactionAllowedCode = terminalTransactionAllowedCode;
    }

    public String getAnsiTerminalStateCode()
    {
        return ansiTerminalStateCode;
    }

    public void setAnsiTerminalStateCode( String ansiTerminalStateCode )
    {
        this.ansiTerminalStateCode = ansiTerminalStateCode;
    }

    public String getAnsiTerminalCountyCode()
    {
        return ansiTerminalCountyCode;
    }

    public void setAnsiTerminalCountyCode( String ansiTerminalCountyCode )
    {
        this.ansiTerminalCountyCode = ansiTerminalCountyCode;
    }

    public String getAnsiTerminalCountryCode()
    {
        return ansiTerminalCountryCode;
    }

    public void setAnsiTerminalCountryCode( String ansiTerminalCountryCode )
    {
        this.ansiTerminalCountryCode = ansiTerminalCountryCode;
    }

    public String getTerminalRoutingGroup()
    {
        return terminalRoutingGroup;
    }

    public void setTerminalRoutingGroup( String terminalRoutingGroup )
    {
        this.terminalRoutingGroup = terminalRoutingGroup;
    }
}
