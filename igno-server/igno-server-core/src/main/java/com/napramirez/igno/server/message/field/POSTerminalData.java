package com.napramirez.igno.server.message.field;

/**
 * POS Terminal Data - Field 60 in FIS ISO Speicification
 * 
 * @author ztorres
 *
 */
public class POSTerminalData
{
    private static final int FIELD_LENGTH = 19;
    private String terminalOwnerFIID;       // 4-7
    private String terminalLogicalNetwork;  // 8-11
    private String terminalOwnerTimeOffset; // 12-15 ( minutes )
    private String pseudoTerminalID;        // 16-19
    
    public POSTerminalData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() !=  FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "POS Terminal Data is invalid" );
        }
        
        terminalOwnerFIID = fieldStringValue.substring( 3, 7 );
        terminalLogicalNetwork = fieldStringValue.substring( 7, 11 );
        terminalOwnerTimeOffset = fieldStringValue.substring( 11, 15 );
        pseudoTerminalID = fieldStringValue.substring( 15 );
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

    public String getPseudoTerminalID()
    {
        return pseudoTerminalID;
    }

    public void setPseudoTerminalID( String pseudoTerminalID )
    {
        this.pseudoTerminalID = pseudoTerminalID;
    }
}