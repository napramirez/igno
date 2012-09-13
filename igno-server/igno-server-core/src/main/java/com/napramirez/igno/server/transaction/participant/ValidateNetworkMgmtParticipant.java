/**
 * 
 */
package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.TransactionParticipant;

import com.napramirez.igno.server.common.ValidateRequest;
import com.napramirez.igno.server.common.ValidateRequestHelper;
import com.napramirez.igno.server.transaction.TransactionContext;

/**
 * Validates the 0800 Network Management Request
 * 
 * @author ztorres
 *
 */
public class ValidateNetworkMgmtParticipant 
	implements TransactionParticipant
{
	private static final int MESSAGE_TYPE = 0;
	private static final int SECONDARY_BIT_MAP = 1;
	private static final int TRANSMISSION_DATE_TIME = 7;
	private static final int SYSTEM_TRACE_AUDIT = 11;
	private static final int SECURITY_RELATED_CONTROL_INFO = 53;
	private static final int PRIMARY_MAC = 64;
	private static final int NETWORK_MGMT_CODE = 70;
	private static final int BASE24_KEY_MGMT = 120;
	private static final int CRYPTOGRAPHIC_SERVICE_MSG = 123;
	private static final int SECONDARY_MAC = 128;
	
	public void abort(long arg0, Serializable arg1)
	{
		// TODO Auto-generated method stub
		
	}

	public void commit(long id, Serializable context)
	{
		TransactionContext ctx = (TransactionContext) context;
        ISOMsg isoMsg = (ISOMsg) ctx.get("request");
        String message = null;
        
        message = isoMsg.getString(MESSAGE_TYPE);
        
        try {
        	// TODO review for format consideration
        	
        	if (!StringUtils.isEmpty(message))
        	{
        		
        	}
        	message = isoMsg.getString(SECONDARY_BIT_MAP);
        	if (!StringUtils.isEmpty(message))
        	{
    			if (message.length() != 16)
    			{
    				throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
    			}
    			if (!StringUtils.isAlphanumeric(message))
    			{
    				throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC);
    			}
        	}
        	message = isoMsg.getString(TRANSMISSION_DATE_TIME);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 10)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(SYSTEM_TRACE_AUDIT);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 6)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(SECURITY_RELATED_CONTROL_INFO);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 16)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(PRIMARY_MAC);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 16)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(NETWORK_MGMT_CODE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 3)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(BASE24_KEY_MGMT);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 9)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS);
        		}
        	}
        	message = isoMsg.getString(CRYPTOGRAPHIC_SERVICE_MSG);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() > 553)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS);
        		}
        	}
        	message = isoMsg.getString(SECONDARY_MAC);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 16)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC);
        		}
        	}
        } 
        catch ( Exception e )
        {
        	e.printStackTrace();
        }
	}

	public int prepare(long arg0, Serializable arg1)
	{
		return PREPARED;
	}
}
