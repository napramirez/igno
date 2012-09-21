package com.napramirez.igno.server.common.field;

/**
 * DepositoryType - Field 124 in FIS ISO Specifications
 *
 * ATM
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class DepositoryType
{
    public enum Type
    {
        NORMAL_ENVELOPE_DEPOSITORY( "0" ),
        COMMERCIAL_DEPOSITORY( "1" ),
        BOTH_NORMAL_AND_COMMERCIAL_DEPOSITORY( "2" );

        private String code;

        Type( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    private static final int FIELD_LENGTH = 4;

    private String fieldLengthIndicator;

    private String depositoryType;

    public DepositoryType( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Depository Type field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        depositoryType = fieldStringValue.substring( 3 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getDepositoryType()
    {
        return depositoryType;
    }

    public void setDepositoryType( String depositoryType )
    {
        this.depositoryType = depositoryType;
    }
}
