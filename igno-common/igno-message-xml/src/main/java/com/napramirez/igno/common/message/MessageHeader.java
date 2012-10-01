package com.napramirez.igno.common.message;

import org.jpos.iso.ISOUtil;

/**
 * MessageHeader
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public enum MessageHeader
{
    NETWORK_MANAGEMENT( "ISO006000000" ),
    ATM_MESSAGE( "ISO016000000" ),
    POS_MESSAGE( "ISO026000000" ),
    FROM_HOST_MAINTENANCE( "ISO086000000" );

    private String header;

    private MessageHeader( String header )
    {
        this.header = header;
    }

    public String toString()
    {
        return header;
    }

    public String toHexString()
    {
        return ISOUtil.hexString( header.getBytes() );
    }
}
