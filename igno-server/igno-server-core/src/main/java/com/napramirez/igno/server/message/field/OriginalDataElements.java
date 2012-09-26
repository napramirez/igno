package com.napramirez.igno.server.message.field;

/**
 * Original Data Elements - Field 90 in FIS ISO Specification
 * 
 * @author ztorres
 *
 */
public class OriginalDataElements
{
    private static final int FIELD_LENGTH = 42;
    private String originalTransactionType;  // 1-4
    private String originalSequenceNumber;   // 5-16
    private String transactionData;          // 17-20
    private String transactionTime;          // 21-28 ( 6 byte time )
    private String originalCaptureDate;      // 29-32  Filler: 33-42
    
    public OriginalDataElements( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Original Data Elements is invalid" );
        }
        
        originalTransactionType = fieldStringValue.substring( 0, 4 );
        originalSequenceNumber = fieldStringValue.substring( 4, 16 );
        transactionData = fieldStringValue.substring( 16, 20 );
        transactionTime = fieldStringValue.substring( 20, 28 );
        originalCaptureDate = fieldStringValue.substring( 28, 32 );
    }

    public String getOriginalTransactionType()
    {
        return originalTransactionType;
    }

    public void setOriginalTransactionType( String originalTransactionType )
    {
        this.originalTransactionType = originalTransactionType;
    }

    public String getOriginalSequenceNumber()
    {
        return originalSequenceNumber;
    }

    public void setOriginalSequenceNumber( String originalSequenceNumber )
    {
        this.originalSequenceNumber = originalSequenceNumber;
    }

    public String getTransactionData()
    {
        return transactionData;
    }

    public void setTransactionData( String transactionData )
    {
        this.transactionData = transactionData;
    }

    public String getTransactionTime()
    {
        return transactionTime;
    }

    public void setTransactionTime( String transactionTime )
    {
        this.transactionTime = transactionTime;
    }

    public String getOriginalCaptureDate()
    {
        return originalCaptureDate;
    }

    public void setOriginalCaptureDate( String originalCaptureDate )
    {
        this.originalCaptureDate = originalCaptureDate;
    }
}