package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.GroupSelector;
import org.jpos.transaction.TransactionParticipant;

import com.napramirez.igno.server.transaction.TransactionContext;

/**
 * MessageSwitchParticipant routes a message to the appropriate group.
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class MessageSwitchParticipant
    implements TransactionParticipant, GroupSelector, Configurable
{
    private static final String HEADER_START = "ISO";

    private static final String HEADER_PRODUCT_INDICATOR = "xx";

    private static final String HEADER_RELEASE_NUMBER = "60";

    private static final String HEADER_STATUS = "000";

    private static final String HEADER_ORIGINATOR_CODE = "y";

    private static final String HEADER_RESPONDER_CODE = "z";

    private static final String GROUP_ATM_SUFFIX = "a";

    private static final String GROUP_POS_SUFFIX = "p";

    private static final String GROUP_UNHANDLED = "Unhandled";

    private static final String MTI_NETWORK_MANAGEMENT = "0800";

    private static final String MTI_FROM_HOST_MAINTENANCE = "0300";

    private static final byte BYTE_ZERO = 0x30;

    private static final byte BYTE_ONE = 0x31;

    private static final byte BYTE_TWO = 0x32;

    private static final byte BYTE_EIGHT = 0x38;

    private Configuration cfg;

    public void setConfiguration( Configuration cfg )
        throws ConfigurationException
    {
        this.cfg = cfg;
    }

    public int prepare( long id, Serializable context )
    {
        return PREPARED | READONLY | NO_JOIN;
    }

    public void commit( long id, Serializable context )
    {
    }

    public void abort( long id, Serializable context )
    {
    }

    public String select( long id, Serializable context )
    {
        TransactionContext ctx = (TransactionContext) context;
        ISOMsg request = (ISOMsg) ctx.get( "request" );

        byte[] pi = getProductIndicator( request.getHeader() );

        StringBuffer group = new StringBuffer();
        try
        {
            String mti = request.getMTI();

            if ( pi[0] == BYTE_ZERO )
            {
                switch ( pi[1] )
                {
                    case BYTE_ZERO:
                    {
                        group.append( MTI_NETWORK_MANAGEMENT.equals( mti ) ? mti : GROUP_UNHANDLED );
                        break;
                    }
                    case BYTE_ONE:
                    {
                        group.append( mti );
                        group.append( GROUP_ATM_SUFFIX );
                        break;
                    }
                    case BYTE_TWO:
                    {
                        group.append( mti );
                        group.append( GROUP_POS_SUFFIX );
                        break;
                    }
                    case BYTE_EIGHT:
                    {
                        group.append( MTI_FROM_HOST_MAINTENANCE.equals( mti ) ? mti : GROUP_UNHANDLED );
                        break;
                    }
                    default:
                    {
                        group.append( GROUP_UNHANDLED );
                        break;
                    }
                }
            }
        }
        catch ( ISOException e )
        {
            group.append( GROUP_UNHANDLED );
        }

        return cfg.get( group.toString(), GROUP_UNHANDLED );
    }

    private byte[] getProductIndicator( byte[] header )
    {
        byte[] hs = new byte[HEADER_START.length()];
        byte[] pi = new byte[HEADER_PRODUCT_INDICATOR.length()];
        byte[] rn = new byte[HEADER_RELEASE_NUMBER.length()];
        byte[] st = new byte[HEADER_STATUS.length()];
        byte[] oc = new byte[HEADER_ORIGINATOR_CODE.length()];
        byte[] rc = new byte[HEADER_RESPONDER_CODE.length()];

        int hsIdx = 0;
        int piIdx = hsIdx + HEADER_START.length();
        int rnIdx = piIdx + HEADER_PRODUCT_INDICATOR.length();
        int stIdx = rnIdx + HEADER_RELEASE_NUMBER.length();
        int ocIdx = stIdx + HEADER_STATUS.length();
        int rcIdx = ocIdx + HEADER_ORIGINATOR_CODE.length();

        System.arraycopy( header, hsIdx, hs, 0, HEADER_START.length() );
        System.arraycopy( header, piIdx, pi, 0, HEADER_PRODUCT_INDICATOR.length() );
        System.arraycopy( header, rnIdx, rn, 0, HEADER_RELEASE_NUMBER.length() );
        System.arraycopy( header, stIdx, st, 0, HEADER_STATUS.length() );
        System.arraycopy( header, ocIdx, oc, 0, HEADER_ORIGINATOR_CODE.length() );
        System.arraycopy( header, rcIdx, rc, 0, HEADER_RESPONDER_CODE.length() );

        return pi;
    }
}
