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
 * @author ztorres
 *
 */
public class ValidateAuthorizationParticipant 
	implements TransactionParticipant
{
	private static final int SECONDARY_BIT_MAP = 1;
	private static final int PAN = 2;
	private static final int PROCESSING_CODE = 3;
	private static final int TRANSACTION_AMOUNT = 4;
	private static final int TRANSMISSION_DATE_TIME = 7;
	private static final int SYSTEM_TRACE_AUDIT_NUMBER = 11;
	private static final int LOCAL_TRANSACTION_TIME = 12;
	private static final int LOCAL_TRANSACTION_DATE = 13;
	private static final int EXPIRATION_DATE = 14;
	private static final int SETTLEMENT_DATE = 15;
	private static final int CAPTURE_DATE = 17;
	private static final int MERCHANT_TYPE = 18;
	private static final int POINT_SERVICE_ENTRY_MODE = 22;
	private static final int POS_CONDITION_CODE = 25;
	private static final int AUTH_ID_RESPONSE_LENGTH = 27;
	private static final int ACQUIRING_INSTITUTION_IDENTIFICAITON_CODE = 32;
	private static final int TRACK2_DATA = 35;
	private static final int RETRIEVAL_REFERENCE_NUMBER = 37;
	private static final int CARD_ACCEPTOR_TERM_ID = 41;
	private static final int CARD_ACCEPTOR_ID_CODE = 42;
	private static final int CARD_ACCEPTOR_NAME_LOCATION = 43;
	private static final int ADDITIONAL_DATA_1 = 48;
	private static final int TRANSACTION_CURRENCY_CODE = 49;
	private static final int PIN_DATA = 52;
	private static final int TERMINAL_DATA = 60;
	private static final int CARD_ISSUER_AUTHORIZER_DATA = 61; 
	private static final int ADDITION_DATA_2 = 63; 
	private static final int PRIMARY_MAC = 64;
	private static final int RECEIVING_INSTITUTION_ID_CODE = 100;
	private static final int TERMINAL_NAME_BRANCH = 120;
	private static final int AUTHORIZATION_INDICATORS = 121;
	private static final int INVOICE_DATA = 123;
	private static final int BATCH_SHIFT_DATA = 124;
	private static final int SETTLEMENT_DATA = 125;
	private static final int PREAUTHORIZATION_CHARGEBACK_DATA = 126;
	private static final int SECONDARY_MAC = 128;

	/* (non-Javadoc)
	 * @see org.jpos.transaction.TransactionParticipant#abort(long, java.io.Serializable)
	 */
	public void abort(long arg0, Serializable arg1)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.jpos.transaction.TransactionParticipant#commit(long, java.io.Serializable)
	 */
	public void commit(long id, Serializable context)
	{
		TransactionContext ctx = (TransactionContext) context;
        ISOMsg isoMsg = (ISOMsg) ctx.get("request");
        String message = null;
       
        try
        {
        	message = isoMsg.getString(SECONDARY_BIT_MAP);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 16)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Secondary Bit Map");
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC + " for Secondary Bit Map");
        		}
        	}
        	message = isoMsg.getString(PAN);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() > 21)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Primary Account Number");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for Primary Account Number");
        		}
        	}		
        	message = isoMsg.getString(PROCESSING_CODE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 6)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Processing Code");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for Processing Code");
        		}
        	}		
        	message = isoMsg.getString(TRANSACTION_AMOUNT);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 12)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Transaction Amount");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for Transaction Amount");
        		}
        	}		
        	message = isoMsg.getString(TRANSMISSION_DATE_TIME);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 10)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Transmission Date and Time");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for Transmission Date and Time");
        		}
        	}	
        	message = isoMsg.getString(SYSTEM_TRACE_AUDIT_NUMBER);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 6)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for System Trace Audit Number");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for System Trace Audit Number");
        		}
        	}	
        	message = isoMsg.getString(LOCAL_TRANSACTION_TIME);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 6)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Local Transaction Time");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for Local Transaction Time");
        		}
        	}		
        	message = isoMsg.getString(LOCAL_TRANSACTION_DATE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 4)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Local Transaction Date");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for Local Transaction Date");
        		}
        	}	
        	message = isoMsg.getString(EXPIRATION_DATE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 4)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Expiration Date");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for Expiration Date");
        		}
        	}
        	message = isoMsg.getString(SETTLEMENT_DATE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 4)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Settlement Date");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for Settlement Date");
        		}
        	}	
        	message = isoMsg.getString(CAPTURE_DATE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 4)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Capture Date");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for Capture Date");
        		}
        	}
        	message = isoMsg.getString(MERCHANT_TYPE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 4)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Merchant Type");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for Merchant Type");
        		}
        	}
        	message = isoMsg.getString(POINT_SERVICE_ENTRY_MODE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 3)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Point of Service Entry Mode");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for Point of Service Entry Mode");
        		}
        	}
        	message = isoMsg.getString(POS_CONDITION_CODE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 2)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for POS Condition Code");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for POS Condition Code");
        		}
        	}
        	message = isoMsg.getString(AUTH_ID_RESPONSE_LENGTH);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 1)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Auth ID Response Length");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for Auth ID Response Length");
        		}
        	}
        	message = isoMsg.getString(ACQUIRING_INSTITUTION_IDENTIFICAITON_CODE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() > 13)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Acquiring Institution Identification Code");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for Acquiring Institution Identification Code");
        		}
        	}	
        	message = isoMsg.getString(TRACK2_DATA);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() > 39)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Track 2 Data");
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS + " for Track 2 Data");
        		}
        	}
        	message = isoMsg.getString(RETRIEVAL_REFERENCE_NUMBER);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 12)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Retrieval Reference Number");
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC + " for Retrieval Reference Number");
        		}
        	}
        	message = isoMsg.getString(CARD_ACCEPTOR_TERM_ID);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 16)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Card Acceptor Term ID");
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS + " for Card Acceptor Term ID");
        		}
        	}
        	message = isoMsg.getString(CARD_ACCEPTOR_ID_CODE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 15)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Card Acceptor ID Code");
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS + " for Card Acceptor ID Code");
        		}
        	}
        	message = isoMsg.getString(CARD_ACCEPTOR_NAME_LOCATION);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 40)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Card Acceptor Name/Location");
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS + " for Card Acceptor Name/Location");
        		}
        	}
        	message = isoMsg.getString(ADDITIONAL_DATA_1);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 30)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Additional Data");
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS + " for Additional Data");
        		}
        	}
        	message = isoMsg.getString(TRANSACTION_CURRENCY_CODE);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 3)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Transaction Currency Code");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for Transaction Currency Code");
        		}
        	}
        	message = isoMsg.getString(PIN_DATA);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 16)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for PIN Data");
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC + " for PIN Data");
        		}
        	}
        	message = isoMsg.getString(TERMINAL_DATA);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 19)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Terminal Data");
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS + " for Terminal Data");
        		}
        	}
        	message = isoMsg.getString(CARD_ISSUER_AUTHORIZER_DATA);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 22)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Card Issuer and Authorizer Data");
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS + " for Card Issuer and Authorizer Data");
        		}
        	}
        	message = isoMsg.getString(ADDITION_DATA_2);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 600)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Additional Data");
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS + " for Additional Data");
        		}
        	}
        	message = isoMsg.getString(PRIMARY_MAC);
        	if (StringUtils.isEmpty(message))
        	{
        		if (message.length() != 16)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Primary Mac");
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC + " for Primary Mac");
        		}
        	}
        	message = isoMsg.getString(RECEIVING_INSTITUTION_ID_CODE);
        	if (StringUtils.isEmpty(message))
        	{
        		if (message.length() > 13)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Receiving Institution ID Code");
        		}
        		if (!StringUtils.isNumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_NUMERIC + " for Recieving Institution ID Code");
        		}
        	}
        	message = isoMsg.getString(TERMINAL_NAME_BRANCH);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 32)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Terminal Name-Branch");
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS + " for Terminal Name-Branch");
        		}
        	}
        	message = isoMsg.getString(AUTHORIZATION_INDICATORS);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 23)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Authorization Indicators");
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS + " for Authorization Indicators");
        		}
        	}
        	message = isoMsg.getString(INVOICE_DATA);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 23)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Invoice Data");
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS + " for Invoice Data");
        		}
        	}
        	message = isoMsg.getString(BATCH_SHIFT_DATA);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 12)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Batch and Shift Data");
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS + " for Batch and Shift Data");
        		}
        	}
        	message = isoMsg.getString(SETTLEMENT_DATA);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 15)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Settlement Data");
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS + " for Settlement Data");
        		}
        	}
        	message = isoMsg.getString(PREAUTHORIZATION_CHARGEBACK_DATA);
        	if (!StringUtils.isEmpty(message))
        	{
        		if (message.length() != 41)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Preauthorization and Chargeback Data");
        		}
        		if (!ValidateRequestHelper.isRegexMatch(message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS + " for Preauthorization and Chargeback Data");
        		}
        	}
        	message = isoMsg.getString(SECONDARY_MAC);
        	if (StringUtils.isEmpty(message))
        	{
        		if (message.length() != 16)
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_LENGTH + " for Secondary Mac");
        		}
        		if (!StringUtils.isAlphanumeric(message))
        		{
        			throw new Exception(ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC + " for Secondary Mac");
        		}
        	}
        }
        catch ( Exception e )
        {
        	e.printStackTrace();
        }
	}

	/* (non-Javadoc)
	 * @see org.jpos.transaction.TransactionParticipant#prepare(long, java.io.Serializable)
	 */
	public int prepare(long arg0, Serializable arg1)
	{
		return PREPARED;
	}

}
