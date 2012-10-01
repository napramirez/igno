package com.napramirez.igno.common.message;

/**
 * MessageTemplate
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public enum MessageTemplate
{
    NETWORK_MANAGEMENT( "0800_t1.xml" ),
    FROM_HOST_MAINTENANCE( "0300_t1.xml" ),
    AUTHORIZATION( "0100_t1.xml" ),
    AUTHORIZATION_ADVICE( "0120_t1.xml" ),
    ATM_FINANCIAL( "0200a_t1.xml" ),
    ATM_FINANCIAL_ADVICE( "0220a_t1.xml" ),
    ATM_REVERSAL_ADVICE( "0420a_t1.xml" ),
    POS_FINANCIAL( "0200p_t1.xml" ),
    POS_FINANCIAL_ADVICE( "0220p_t1.xml" ),
    POS_REVERSAL_ADVICE( "0420p_t1.xml" );

    private String filename;

    private MessageTemplate( String filename )
    {
        this.filename = filename;
    }

    public String toString()
    {
        return filename;
    }
}
