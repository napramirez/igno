package com.napramirez.igno.server.common.field;

/**
 * BatchAndShiftData - Field 124 in FIS ISO Specifications
 * 
 * POS
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class BatchAndShiftData
{
    private static final int FIELD_LENGTH = 12;

    private String fieldLengthIndicator;

    private String batchTransactionSequenceNumber;

    private String batchNumber;

    private String shiftNumber;

    public BatchAndShiftData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Batch and Shift Data field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        batchTransactionSequenceNumber = fieldStringValue.substring( 3, 6 );
        batchNumber = fieldStringValue.substring( 6, 9 );
        shiftNumber = fieldStringValue.substring( 9 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getBatchTransactionSequenceNumber()
    {
        return batchTransactionSequenceNumber;
    }

    public void setBatchTransactionSequenceNumber( String batchTransactionSequenceNumber )
    {
        this.batchTransactionSequenceNumber = batchTransactionSequenceNumber;
    }

    public String getBatchNumber()
    {
        return batchNumber;
    }

    public void setBatchNumber( String batchNumber )
    {
        this.batchNumber = batchNumber;
    }

    public String getShiftNumber()
    {
        return shiftNumber;
    }

    public void setShiftNumber( String shiftNumber )
    {
        this.shiftNumber = shiftNumber;
    }
}
