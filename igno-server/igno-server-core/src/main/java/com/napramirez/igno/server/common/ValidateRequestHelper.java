package com.napramirez.igno.server.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author ztorres
 *
 */
public class ValidateRequestHelper
{

	public static boolean isRegexMatch(String txMessage, String regex)
	{
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(txMessage);
		boolean valid = m.find();
		
		return valid;
	}
}
