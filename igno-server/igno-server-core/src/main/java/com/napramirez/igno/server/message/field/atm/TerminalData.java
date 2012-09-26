package com.napramirez.igno.server.message.field.atm;

/**
 * TerminalData - Field 060 in FIS ISO Specifications
 *
 * ATM
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class TerminalData
{
    private static final int FIELD_LENGTH = 15;

    private String fieldLengthIndicator;

    private String terminalOwnerFIID;

    private String terminalLogicalNetwork;

    private String terminalOwnerTimeOffset;

    public TerminalData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "ATM Terminal Data field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        terminalOwnerFIID = fieldStringValue.substring( 3, 7 );
        terminalLogicalNetwork = fieldStringValue.substring( 7, 11 );
        terminalOwnerTimeOffset = fieldStringValue.substring( 11 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getTerminalOwnerFIID()
    {
        return terminalOwnerFIID;
    }

    public void setTerminalOwnerFIID( String terminalOwnerFIID )
    {
        this.terminalOwnerFIID = terminalOwnerFIID;
    }

    public String getTerminalLogicalNetwork()
    {
        return terminalLogicalNetwork;
    }

    public void setTerminalLogicalNetwork( String terminalLogicalNetwork )
    {
        this.terminalLogicalNetwork = terminalLogicalNetwork;
    }

    public String getTerminalOwnerTimeOffset()
    {
        return terminalOwnerTimeOffset;
    }

    public void setTerminalOwnerTimeOffset( String terminalOwnerTimeOffset )
    {
        this.terminalOwnerTimeOffset = terminalOwnerTimeOffset;
    }
}
