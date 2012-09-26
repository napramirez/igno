package com.napramirez.igno.server.message.field;

/**
 * ATMTerminalName - Field 120 in FIS ISO Specifications
 *
 * Terminal Address-Branch-Region (ATM)
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class ATMTerminalName
{
    private static final int FIELD_LENGTH = 36;

    private String fieldLengthIndicator;

    private String terminalAddress;

    private String terminalBranch;

    private String terminalRegion;

    public ATMTerminalName( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Terminal Name (ATM) field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        terminalAddress = fieldStringValue.substring( 3, 28 );
        terminalBranch = fieldStringValue.substring( 28, 32 );
        terminalRegion = fieldStringValue.substring( 32 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getTerminalAddress()
    {
        return terminalAddress;
    }

    public void setTerminalAddress( String terminalAddress )
    {
        this.terminalAddress = terminalAddress;
    }

    public String getTerminalBranch()
    {
        return terminalBranch;
    }

    public void setTerminalBranch( String terminalBranch )
    {
        this.terminalBranch = terminalBranch;
    }

    public String getTerminalRegion()
    {
        return terminalRegion;
    }

    public void setTerminalRegion( String terminalRegion )
    {
        this.terminalRegion = terminalRegion;
    }
}
