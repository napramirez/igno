package com.napramirez.igno.server.transaction.participant;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.TransactionParticipant;

import com.napramirez.igno.server.common.ValidateRequest;
import com.napramirez.igno.server.common.ValidateRequestHelper;
import com.napramirez.igno.server.transaction.TransactionContext;

/**
 * Validates the 0200 ATM Financial Transaction Request
 * 
 * @author ztorres
 *
 */
public class ValidateATMFinancialTxParticipant 
	implements TransactionParticipant
{
    private static final int SECONDARY_BIT_MAP = 1;
    private static final int PAN = 2;
    private static final int PROCESSING_CODE = 3;
    private static final int TRANSACTION_AMOUNT = 4;
    private static final int TRANSMISSION_DATE_TIME = 7;
    private static final int SYSTEM_TRACE_AUDIT_NUMBER = 11;
    private static final int LOCAL_TRANSACTION_TIME = 12;
    private static final int LOCAL_TRASACTION_DATE = 13;
    private static final int SETTLEMENT_DATE = 15;
    private static final int CAPTURE_DATE = 17;
    private static final int POINT_SERVICE_ENTRY_MODE = 22;
    private static final int TRANSACTION_FEE_AMOUNT = 28;
    private static final int ACQUIRING_INSTITUTION_IDENTIFICATION_CODE = 32;
    private static final int TRACK2_DATA = 35; 
    private static final int RETRIEVAL_REFERENCE_NUMBER = 37;
    private static final int CARD_ACCEPTOR_TERM_ID = 41;
    private static final int CARD_ACCEPTOR_ID_CODE = 42;
    private static final int CARD_ACCEPTOR_NAME_LOCATION = 43;
    private static final int ADDITIONAL_DATA_1 = 48;
    private static final int TRANSACTION_CURRENCY_CODE = 49;
    private static final int PERSONAL_IDENTIFICATION_NUMBER = 52;
    private static final int ADDITIONAL_AMOUNTS = 54;
    private static final int TERMINAL_DATA = 60;
    private static final int CARD_ISSUER_AUTHORIZER_DATA = 61;
    private static final int PRIMARY_MAC = 64; 
    private static final int RECEIVING_INSTITUTION_ID_CODE = 100;
    private static final int ACCOUNT_IDENTIFICATION1 = 102;
    private static final int ACCOUNT_IDENTIFICATION2 = 103;
    private static final int TERMINAL_ADDRESS = 120;
    private static final int DEPOSITORY_TYPE = 124;
    private static final int ACCOUNT_INDICATOR = 125;
    private static final int ADDITIONAL_DATA_2 = 126;
    private static final int USER_DATA = 127;
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
        
        try
        {
        	// TODO review for format consideration
        	
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
        	message = isoMsg.getString(PAN);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() > 19)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(PROCESSING_CODE);
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
        	message = isoMsg.getString(TRANSACTION_AMOUNT);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 12)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC);
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
        	message = isoMsg.getString(SYSTEM_TRACE_AUDIT_NUMBER);
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
        	message = isoMsg.getString(LOCAL_TRANSACTION_TIME);
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
        	message = isoMsg.getString(LOCAL_TRASACTION_DATE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 4)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(SETTLEMENT_DATE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 4)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(CAPTURE_DATE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 4)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(POINT_SERVICE_ENTRY_MODE);
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
        	message = isoMsg.getString(TRANSACTION_FEE_AMOUNT);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 9)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.CREDIT_DEBIT_AMOUNT))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_AMOUNT);
        		}
        	}
        	message = isoMsg.getString(ACQUIRING_INSTITUTION_IDENTIFICATION_CODE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() > 11)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(TRACK2_DATA);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() > 37)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(RETRIEVAL_REFERENCE_NUMBER);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 12)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(CARD_ACCEPTOR_TERM_ID);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 16)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS);
        		}
        	}
        	message = isoMsg.getString(CARD_ACCEPTOR_ID_CODE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 15)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS);
        		}
        	}
        	message = isoMsg.getString(CARD_ACCEPTOR_NAME_LOCATION);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 40)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS);
        		}
        	}
        	message = isoMsg.getString(ADDITIONAL_DATA_1);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 47)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS);
        		}
        	}
        	message = isoMsg.getString(TRANSACTION_CURRENCY_CODE);
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
        	message = isoMsg.getString(PERSONAL_IDENTIFICATION_NUMBER);
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
        	message = isoMsg.getString(ADDITIONAL_AMOUNTS);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 15)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(TERMINAL_DATA);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 15)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(CARD_ISSUER_AUTHORIZER_DATA);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 16)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS);
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
        	message = isoMsg.getString(RECEIVING_INSTITUTION_ID_CODE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() > 11)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(ACCOUNT_IDENTIFICATION1);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() > 30)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(ACCOUNT_IDENTIFICATION2);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() > 30)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(TERMINAL_ADDRESS);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 36)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS);
        		}
        	}
        	message = isoMsg.getString(DEPOSITORY_TYPE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 4)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(ACCOUNT_INDICATOR);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 4)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(ADDITIONAL_DATA_2);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 800)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC);
        		}
        	}
        	message = isoMsg.getString(USER_DATA);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() > 60)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH);
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC);
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
