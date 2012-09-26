package com.napramirez.igno.server.message.field.pos;

/**
 * InvoiceData - Field 123 in FIS ISO Specifications POS
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class InvoiceData
{
    private static final int FIELD_LENGTH = 23;

    private String fieldLengthIndicator;

    private String invoiceData;

    private String originalTransactionInvoiceData;

    public InvoiceData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Additional Data field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        invoiceData = fieldStringValue.substring( 3, 13 );
        originalTransactionInvoiceData = fieldStringValue.substring( 13 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getInvoiceData()
    {
        return invoiceData;
    }

    public void setInvoiceData( String invoiceData )
    {
        this.invoiceData = invoiceData;
    }

    public String getOriginalTransactionInvoiceData()
    {
        return originalTransactionInvoiceData;
    }

    public void setOriginalTransactionInvoiceData( String originalTransactionInvoiceData )
    {
        this.originalTransactionInvoiceData = originalTransactionInvoiceData;
    }
}
