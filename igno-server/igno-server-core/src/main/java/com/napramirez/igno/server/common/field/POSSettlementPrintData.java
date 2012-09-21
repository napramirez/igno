package com.napramirez.igno.server.common.field;

/**
 * POSSettlementPrintData - Field 125 in FIS ISO Specifications
 *
 * POS
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class POSSettlementPrintData
{
    private static final int FIELD_LENGTH = 15;

    private String fieldLengthIndicator;

    private String services;

    private String originator;

    private String destination;

    private String draftCaptureFlag;

    private String settlementFlag;

    public POSSettlementPrintData( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Settlement Print Data (POS) field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        services = fieldStringValue.substring( 3, 5 );
        originator = fieldStringValue.substring( 5, 9 );
        destination = fieldStringValue.substring( 9, 13 );
        draftCaptureFlag = fieldStringValue.substring( 13, 14 );
        settlementFlag = fieldStringValue.substring( 14 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getServices()
    {
        return services;
    }

    public void setServices( String services )
    {
        this.services = services;
    }

    public String getOriginator()
    {
        return originator;
    }

    public void setOriginator( String originator )
    {
        this.originator = originator;
    }

    public String getDestination()
    {
        return destination;
    }

    public void setDestination( String destination )
    {
        this.destination = destination;
    }

    public String getDraftCaptureFlag()
    {
        return draftCaptureFlag;
    }

    public void setDraftCaptureFlag( String draftCaptureFlag )
    {
        this.draftCaptureFlag = draftCaptureFlag;
    }

    public String getSettlementFlag()
    {
        return settlementFlag;
    }

    public void setSettlementFlag( String settlementFlag )
    {
        this.settlementFlag = settlementFlag;
    }
}
