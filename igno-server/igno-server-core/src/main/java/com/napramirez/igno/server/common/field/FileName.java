package com.napramirez.igno.server.common.field;

/**
 * FileNameCode - Field 101 in FIS ISO Specifications
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class FileName
{
    /**
     * File Name - Position 3-4
     */
    public enum FileNameCode
    {
        POSITIVE_BALANCE_FILE( "CC" ),
        CARDHOLDER_AUTHORIZATION_FILE( "CF" ),
        NEGATIVE_CARD_FILE( "NF" );

        private String code;

        private FileNameCode( String code )
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

    private String filenameCode;

    public FileName( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "File Name field is invalid!" );
        }

        fieldLengthIndicator = fieldStringValue.substring( 0, 3 );
        filenameCode = fieldStringValue.substring( 3 );
    }

    public String getFieldLengthIndicator()
    {
        return fieldLengthIndicator;
    }

    public void setFieldLengthIndicator( String fieldLengthIndicator )
    {
        this.fieldLengthIndicator = fieldLengthIndicator;
    }

    public String getFilenameCode()
    {
        return filenameCode;
    }

    public void setFilenameCode( String filenameCode )
    {
        this.filenameCode = filenameCode;
    }
}
