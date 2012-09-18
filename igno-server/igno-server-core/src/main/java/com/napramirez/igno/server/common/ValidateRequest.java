/**
 * 
 */
package com.napramirez.igno.server.common;

/**
 * @author ztorres
 *
 */
public class ValidateRequest
{
	public static interface RegEx
	{
	    String ALPHA_NUMERIC_SPECIAL_CHAR = "^[a-zA-Z0-9!@#$%^&*()_ ]*$";
	    String CREDIT_DEBIT_AMOUNT = "^(C|D)\\d{8}";
	}
	
	public static interface ErrorMsg
	{
	    String INVALID_LENGTH = "Invalid length of characters";
	    String INVALID_NUMERIC = "Invalid numeric values";
	    String INVALID_ALPHA_NUMERIC = "Invalid alphanumeric values";
	    String INVALID_ALPHA_NUMERIC_SPECIAL_CHARS = "Invalid alphanumeric with special characters values";
	    String INVALID_AMOUNT = "Invalid amount";
	    String INVALID_ALPHABETIC = "Invalid alphabetic values";
	    String MISSING_FIELD = "Missing field";
	}
	
    public static interface StandardNotation
    {
        String ALPHABETIC = "ALPHABETIC";
        String NUMERIC = "NUMERIC";
        String ALPHABETIC_NUMERIC = "ALPHABETIC_NUMERIC";
        String ALPHABETIC_NUMERIC_SPECIAL_CHARACTERS = "ALPHABETIC_NUMERIC_SPECIAL_CHARACTERS";
        String CREDIT_DEBIT_NUMERIC = "CREDIT_DEBIT_NUMERIC";
    }
}
