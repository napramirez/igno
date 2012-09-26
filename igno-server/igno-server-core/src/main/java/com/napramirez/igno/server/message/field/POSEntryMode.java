package com.napramirez.igno.server.message.field;

/**
 * POSEntryMode - Field 022 in FIS ISO Specifications
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public final class POSEntryMode
{
    /**
     * PAN Entry Mode - Position 1-2
     */
    public enum PAN
    {
        UNSPECIFIED( "00" ),
        MANUAL( "01" ),
        MAGNETIC_STRIPE_READ( "02" ),
        BAR_CODE_READER( "03" ),
        OPTICAL_CHARACTER_READER( "04" ),
        INTEGRATED_CIRCUIT_BOARD( "05" ),
        MANUALLY_ENTERED( "06" ),
        FULL_MAGNETIC_STRIPE_READ( "90" );

        private String code;

        PAN( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    /**
     * PIN Entry Mode - Position 3
     */
    public enum PIN
    {
        UNSPECIFIED( "0" ),
        PIN_ENTRY_CAPABLE( "1" ),
        NO_PIN_ENTRY_CAPABLE( "2" ),
        PIN_PAD_INOPERABLE( "6" );

        private String code;

        PIN( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    private static final int FIELD_LENGTH = 3;

    private String panEntryMode;

    private String pinEntryMode;

    public POSEntryMode( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "POS Entry Mode field is invalid!" );
        }

        panEntryMode = fieldStringValue.substring( 0, 2 );
        pinEntryMode = fieldStringValue.substring( 2 );
    }

    public String getPanEntryMode()
    {
        return panEntryMode;
    }

    public void setPanEntryMode( String panEntryMode )
    {
        this.panEntryMode = panEntryMode;
    }

    public String getPinEntryMode()
    {
        return pinEntryMode;
    }

    public void setPinEntryMode( String pinEntryMode )
    {
        this.pinEntryMode = pinEntryMode;
    }
}
