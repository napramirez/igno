package com.napramirez.igno.server.common.field;

/**
 * ATMSettlementPrintData - Field 125 in FIS ISO Specifications
 *
 * ATM
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class ATMSettlementPrintData
{
    private static final int FIELD_LENGTH = 375;

    private String fieldLengthIndicator;
    
    private String pageIndicator;
    
    private String lastStatementDate;
    
    private String headerLines;
    
    private String columnLines;
    
    private String statementData;
    
    public ATMSettlementPrintData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Settlement Print Data (ATM) field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        pageIndicator = fieldStringValue.substring( 3, 5 );
        lastStatementDate = fieldStringValue.substring( 5, 11 );
        headerLines = fieldStringValue.substring( 11, 13 );
        columnLines = fieldStringValue.substring( 13, 15 );
        statementData = fieldStringValue.substring( 15 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getPageIndicator()
    {
        return pageIndicator;
    }

    public void setPageIndicator( String pageIndicator )
    {
        this.pageIndicator = pageIndicator;
    }

    public String getLastStatementDate()
    {
        return lastStatementDate;
    }

    public void setLastStatementDate( String lastStatementDate )
    {
        this.lastStatementDate = lastStatementDate;
    }

    public String getHeaderLines()
    {
        return headerLines;
    }

    public void setHeaderLines( String headerLines )
    {
        this.headerLines = headerLines;
    }

    public String getColumnLines()
    {
        return columnLines;
    }

    public void setColumnLines( String columnLines )
    {
        this.columnLines = columnLines;
    }

    public String getStatementData()
    {
        return statementData;
    }

    public void setStatementData( String statementData )
    {
        this.statementData = statementData;
    }
}
