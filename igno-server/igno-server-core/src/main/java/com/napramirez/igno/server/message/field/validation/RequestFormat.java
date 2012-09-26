/**
 * 
 */
package com.napramirez.igno.server.message.field.validation;

/**
 * @author ztorres
 *
 */
public class RequestFormat 
{
    private int maxLength;
    private String attribute;
    private boolean fixed;
	    
    public static interface RegEx
    {
	    String ALPHA_NUMERIC_SPECIAL = "^[a-zA-Z0-9!@#$%^&*()_ =]*$";
	    String CREDIT_DEBIT_AMOUNT = "^(C|D)\\d{8}$"; // used for X+N8 attribute
	    String ALPHABETIC_NUMERIC = "^[a-zA-Z0-9 ]*$";
	    String NUMERIC = "^[\\d]*$";
	    String AMOUNT = "^[\\d]*\\.{0,1}[\\d]*$";
	}
	
	public static interface ErrorMsg
	{
	    String INVALID_LENGTH = "Invalid length of characters";
	    String INVALID_NUMERIC = "Invalid numeric values";
	    String INVALID_ALPHABETIC_NUMERIC = "Invalid alphanumeric values";
	    String INVALID_ALPHABETIC_NUMERIC_SPECIAL = "Invalid alphanumeric with special characters values";
	    String INVALID_AMOUNT = "Invalid amount";
	    String INVALID_ALPHABETIC = "Invalid alphabetic values";
	    String MISSING_FIELD = "Missing field";
	}
	
    public static interface StandardNotation
    {
        String NUMERIC = "NUMERIC";
        String ALPHABETIC_NUMERIC = "ALPHABETIC_NUMERIC";
        String ALPHABETIC_NUMERIC_SPECIAL = "ALPHABETIC_NUMERIC_SPECIAL";
        String CREDIT_DEBIT_NUMERIC = "CREDIT_DEBIT_NUMERIC";
        String AMOUNT = "AMOUNT";
    }
    
    public RequestFormat( int maxLength, String attribute, boolean fixed )
    {
        this.fixed = fixed;
	    this.maxLength = maxLength;
	    this.attribute = attribute;
	}
	        
    protected int getMaxLength()
	{
	    return maxLength;
	}
	        
    protected String getAttribute()
	{
	    return attribute;
	}
    
    protected boolean getFixed()
	{
	    return fixed;
	}
}
