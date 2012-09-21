package com.napramirez.igno.server.common.field;

/**
 * POSTerminalName - Field 120 in FIS ISO Specifications
 * 
 * Terminal Name/Address-Branch (POS)
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class POSTerminalName
{
    private static final int FIELD_LENGTH = 32;

    private String fieldLengthIndicator;

    private String terminalNameAndLocation;

    private String terminalBranchId;

    public POSTerminalName( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Terminal Name (POS) field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        terminalNameAndLocation = fieldStringValue.substring( 3, 28 );
        terminalBranchId = fieldStringValue.substring( 28 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getTerminalNameAndLocation()
    {
        return terminalNameAndLocation;
    }

    public void setTerminalNameAndLocation( String terminalNameAndLocation )
    {
        this.terminalNameAndLocation = terminalNameAndLocation;
    }

    public String getTerminalBranchId()
    {
        return terminalBranchId;
    }

    public void setTerminalBranchId( String terminalBranchId )
    {
        this.terminalBranchId = terminalBranchId;
    }
}
