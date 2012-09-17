package com.napramirez.igno.server.common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.jpos.iso.ISOFieldPackager;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.BASE24Packager;

/**
 * 
 * @author ztorres
 *
 */
public class ValidateRequestHelper
{
    private static final Map<Integer, String> map = new HashMap<Integer, String>();
    static 
    {
        map.put( 1, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
        map.put( 2, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 3, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 4, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 7, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 11, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 12, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 13, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 14, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 15, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 17, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 18, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 22, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 25, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 27, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 28, ValidateRequest.StandardNotation.CREDIT_DEBIT_NUMERIC );
        map.put( 32, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 35, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
        map.put( 37, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
        map.put( 38, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
        map.put( 39, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
        map.put( 41, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL_CHARACTERS );
        map.put( 42, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL_CHARACTERS );
        map.put( 43, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL_CHARACTERS );
        map.put( 44, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL_CHARACTERS );
        map.put( 45, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL_CHARACTERS );
        map.put( 48, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL_CHARACTERS );
        map.put( 49, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 52, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
        map.put( 53, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 54, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
        map.put( 60, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
        map.put( 61, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL_CHARACTERS );
        map.put( 63, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL_CHARACTERS );
        map.put( 64, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
        map.put( 90, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 100, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 102, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
        map.put( 103, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
        map.put( 120, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL_CHARACTERS );
        map.put( 121, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL_CHARACTERS );
        map.put( 122, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
        map.put( 123, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL_CHARACTERS );
        map.put( 124, ValidateRequest.StandardNotation.NUMERIC );
        map.put( 125, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
        map.put( 126, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
        map.put( 127, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
        map.put( 128, ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC );
    }
    
    public static final void fieldValidation( int[] fields, ISOMsg isoMsg ) throws Exception
    {
    	BASE24Packager base24Packager = ( BASE24Packager ) isoMsg.getPackager();
        for ( int i = 0; i < fields.length; i++ )
        {
            int field = fields[i];
            Integer fieldKey = Integer.valueOf( field );
            String message = isoMsg.getString( field );
            ISOFieldPackager isoFieldPackager = base24Packager.getFieldPackager( field );
            int length = isoFieldPackager.getLength();
            
            // check for message length
            if ( length != message.length() )
            {
                throw new Exception( ValidateRequest.ErrorMsg.INVALID_LENGTH );
            }
            
            // check for format
            if ( ValidateRequest.StandardNotation.ALPHABETIC.equals( map.get( fieldKey ) ) )
            {
                if ( !StringUtils.isAlpha( message ) )
                {
                    throw new Exception( ValidateRequest.ErrorMsg.INVALID_ALPHABETIC );   
                }
            }
            if ( ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC.equals( map.get( fieldKey ) ) )
            {
                if (!StringUtils.isAlphanumeric( message ))
                {
                    throw new Exception( ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC );
                }
            }
            if ( ValidateRequest.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL_CHARACTERS.equals( map.get( fieldKey ) ) )
            {
                if ( !isRegexMatch( message, ValidateRequest.RegEx.ALPHA_NUMERIC_SPECIAL_CHAR ) )
                {
                    throw new Exception( ValidateRequest.ErrorMsg.INVALID_ALPHA_NUMERIC_SPECIAL_CHARS );
                }
            }
            if ( ValidateRequest.StandardNotation.CREDIT_DEBIT_NUMERIC.equals( map.get( fieldKey ) ) )
            {
                if ( !isRegexMatch( message, ValidateRequest.RegEx.CREDIT_DEBIT_AMOUNT ) )
                {
                    throw new Exception( ValidateRequest.ErrorMsg.INVALID_AMOUNT );
                }
            }
            if ( ValidateRequest.StandardNotation.NUMERIC.equals( map.get( fieldKey ) ) )
            {
                if ( !StringUtils.isNumeric( message ) )
                {
                    throw new Exception( ValidateRequest.ErrorMsg.INVALID_NUMERIC );
                }
            }
        }
    }
    
    public static boolean isRegexMatch(String txMessage, String regex)
    {
	    Pattern p = Pattern.compile(regex);
	    Matcher m = p.matcher(txMessage);
	    boolean valid = m.find();
		
	    return valid;
	}
}
