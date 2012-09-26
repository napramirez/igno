package com.napramirez.igno.server.common.validate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jpos.iso.ISOMsg;

import com.napramirez.igno.server.common.constants.ProductIndicator;

/**
 * Validates message request attributes in FIS ISO Specification
 * @author ztorres
 *
 */
public class RequestValidator
{
    private static final Map<String, RequestFormat> map = new HashMap<String, RequestFormat>();
    static
    {
        map.put( "1", new RequestFormat( 16, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );               // 1:   0800, 0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p, 0300
        map.put( "2", new RequestFormat( 19, RequestFormat.StandardNotation.NUMERIC, false ) );                         // 2:         0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p, 0300
        map.put( "3", new RequestFormat( 6, RequestFormat.StandardNotation.NUMERIC, true ) );                           // 3:         0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "4", new RequestFormat( 12, RequestFormat.StandardNotation.AMOUNT, true ) );                           // 4:         0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "7", new RequestFormat( 10, RequestFormat.StandardNotation.NUMERIC, true ) );                          // 7:   0800, 0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p, 0300
        map.put( "11", new RequestFormat( 6, RequestFormat.StandardNotation.NUMERIC, true ) );                          // 11:  0800, 0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p, 0300
        map.put( "12", new RequestFormat( 6, RequestFormat.StandardNotation.NUMERIC, true ) );                          // 12:        0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "13", new RequestFormat( 4, RequestFormat.StandardNotation.NUMERIC, true ) );                          // 13:        0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "14", new RequestFormat( 4, RequestFormat.StandardNotation.NUMERIC, true ) );                          // 14:                                       0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "15", new RequestFormat( 4, RequestFormat.StandardNotation.NUMERIC, true ) );                          // 15:        0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "17", new RequestFormat( 4, RequestFormat.StandardNotation.NUMERIC, true ) );                          // 17:        0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "18", new RequestFormat( 4, RequestFormat.StandardNotation.NUMERIC, true ) );                          // 18:                                       0100,            0200p, 0220/0221p, 0420/0421p
        map.put( "22", new RequestFormat( 3, RequestFormat.StandardNotation.NUMERIC, true ) );                          // 22:        0200a,                         0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "25", new RequestFormat( 2, RequestFormat.StandardNotation.NUMERIC, true ) );                          // 25:                                       0100,            0200p, 0220/0221p, 0420/0421p
        map.put( "27", new RequestFormat( 1, RequestFormat.StandardNotation.NUMERIC, true ) );                          // 27:                                       0100, 0120/0121, 0200p, 0220/0221p
        map.put( "28", new RequestFormat( 9, RequestFormat.StandardNotation.CREDIT_DEBIT_NUMERIC, true ) );             // 28:        0200a, 0220/0221a, 0420/0421a
        map.put( "32", new RequestFormat( 11, RequestFormat.StandardNotation.NUMERIC, false ) );                        // 32:        0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "35", new RequestFormat( 37, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, false ) );             // 35:        0200a, 0220/0221a, 0420/0421a,       0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "35_1", new RequestFormat( 37, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, false ) );   // 35:                                       0100
        map.put( "37", new RequestFormat( 12, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );              // 37:        0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "38", new RequestFormat( 6, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );               // 38:               0220/0221a, 0420/0421a,       0120/0121,        0220/0221p, 0420/0421p
        map.put( "39", new RequestFormat( 2, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );               // 39:               0220/0221a, 0420/0421a,       0120/0121,        0220/0221p
//        map.put( "39_1", new RequestFormat( 16, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );            // 39:                                                                           0420/0421p
        map.put( "41", new RequestFormat( 16, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );      // 41:        0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "42", new RequestFormat( 15, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );      // 42:        0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "43", new RequestFormat( 40, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );      // 43:        0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "44", new RequestFormat( 4, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );       // 44:                                                        0200p, 0220/0221p
        map.put( "45", new RequestFormat( 76, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, false ) );     // 45:                                                        0200p, 0220/0221p, 0420/0421p
        map.put( "48", new RequestFormat( 47, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );      // 48:        0200a
        map.put( "48_1", new RequestFormat( 30, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );    // 48:                                       0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "48_2", new RequestFormat( 79, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );    // 48:                                                                                       0300
        map.put( "49", new RequestFormat( 3, RequestFormat.StandardNotation.NUMERIC, true ) );                          // 49:        0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p, 0300
        map.put( "52", new RequestFormat( 16, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );              // 52:        0200a,                         0100,            0200p
        map.put( "53", new RequestFormat( 16, RequestFormat.StandardNotation.NUMERIC, true ) );                         // 53:  0800
        map.put( "54", new RequestFormat( 15, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );              // 54:        0200a, 0220/0221a, 0420/0421a,                  0200p, 0220/0221p, 0420/0421p
        map.put( "60", new RequestFormat( 15, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );              // 60:        0200a, 0220/0221a, 0420/0421a
        map.put( "60_1", new RequestFormat( 19, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );    // 60:                                       0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "60_2", new RequestFormat( 61, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );    // 60:                                                                                       0300
        map.put( "61", new RequestFormat( 16, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );      // 61:        0200a, 0220/0221a
        map.put( "61_1", new RequestFormat( 22, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );    // 61:                                       0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "61_2", new RequestFormat( 16, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );            // 61:                           0420/0421a
        map.put( "63", new RequestFormat( 19, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );              // 63:               0220/0221a, 0420/0421a
        map.put( "63_1", new RequestFormat( 600, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );   // 63:                                       0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "64", new RequestFormat( 16, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );              // 64:  0800, 0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "70", new RequestFormat( 3, RequestFormat.StandardNotation.NUMERIC, true ) );                          // 70:  0800
        map.put( "90", new RequestFormat( 42, RequestFormat.StandardNotation.NUMERIC, true ) );                         // 90:               0220/0221a, 0420/0421a,                  0200p, 0220/0221p, 0420/0421p
        map.put( "91", new RequestFormat( 1, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );               // 91:                                                                                       0300
        map.put( "95", new RequestFormat( 42, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );              // 95                                                                0220/0221p, 0420/0421p
        map.put( "100", new RequestFormat( 11, RequestFormat.StandardNotation.NUMERIC, false ) );                       // 100:       0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "101", new RequestFormat( 2, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );              // 101:                                                                                      0300
        map.put( "102", new RequestFormat( 30, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, false ) );            // 102:       0200a, 0220/0221a, 0420/0421a,       0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "103", new RequestFormat( 30, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, false ) );            // 103:       0200a, 0220/0221a, 0420/0421a
        map.put( "120", new RequestFormat( 9, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );      // 120: 0800
        map.put( "120_1", new RequestFormat( 32, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );   // 120:                                      0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "120_2", new RequestFormat( 36, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );   // 120:       0200a, 0220/0221a, 0420/0421a, 
        map.put( "120_3", new RequestFormat( 112, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );  // 120:                                                                                      0300
//        map.put( "120_3", new RequestFormat( 18, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) ); // 120 ------> special case for 0300 double length value ( 112 or 18 )
        map.put( "121", new RequestFormat( 23, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );     // 121:                                      0100,            0200p, 0220/0221p, 0420/0421p
        map.put( "121_1", new RequestFormat( 79, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );   // 121:                                                                                      0300
        map.put( "122", new RequestFormat( 14, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );             // 122:              0220/0221a, 0420/0421a,                                     0420/0421p
        map.put( "122_1", new RequestFormat( 14, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );   // 122:                                            0120/0121,        0220/0221p
        map.put( "122_2", new RequestFormat( 120, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );  // 122:                                                                                      0300
        map.put( "123", new RequestFormat( 553, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, false ) );   // 123: 0800
        map.put( "123_1", new RequestFormat( 23, RequestFormat.StandardNotation.NUMERIC, true ) );                      // 123:                                      0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "123_2", new RequestFormat( 15, RequestFormat.StandardNotation.NUMERIC, true ) );                      // 123:              0220/0221a, 0420/0421a
        map.put( "124", new RequestFormat( 4, RequestFormat.StandardNotation.NUMERIC, true ) );                         // 124:       0200a, 0220/0221a
        map.put( "124_1", new RequestFormat( 12, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );   // 124:                                      0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "125", new RequestFormat( 4, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );              // 125:       0200a, 0220/0221a, 0420/0421a
        map.put( "125_1", new RequestFormat( 15, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );   // 125:                                      0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
        map.put( "126", new RequestFormat( 800, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );            // 126:       0200a, 0220/0221a, 0420/0421a,                  0200p, 0220/0221p
        map.put( "126_1", new RequestFormat( 41, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );   // 126:                                      0100, 0120/0121,                    0420/0421p
        map.put( "126_2", new RequestFormat( 693, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, false ) ); // 126:                                                                                      0300
        map.put( "127", new RequestFormat( 60, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, false ) );            // 127:       0200a, 0220/0221a
        map.put( "127_1", new RequestFormat( 32, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL, true ) );   // 127:                                                                                      0300
        map.put( "128", new RequestFormat( 16, RequestFormat.StandardNotation.ALPHABETIC_NUMERIC, true ) );             // 128: 0800, 0200a, 0220/0221a, 0420/0421a, 0100, 0120/0121, 0200p, 0220/0221p, 0420/0421p
    }

    private static final Map<String, String> map0200a = new HashMap<String, String>();
    static
    {
        map0200a.put( "120", "120_2" );
    }

    private static final Map<String, String> map0220a_0221a = new HashMap<String, String>();
    static
    {
        map0220a_0221a.put( "120", "120_2" );
        map0220a_0221a.put( "123", "123_2" );
    }

    private static final Map<String, String> map0420a_0421a = new HashMap<String, String>();
    static
    {
        map0420a_0421a.put( "61", "61_2" );
        map0420a_0421a.put( "120", "120_2" );
        map0420a_0421a.put( "123", "123_2" );
    }

    private static final Map<String, String> map0100 = new HashMap<String, String>();
    static
    {
        map0100.put( "35", "35_1" );
        map0100.put( "48", "48_1" );
        map0100.put( "60", "60_1" );
        map0100.put( "61", "61_1" );
        map0100.put( "63", "63_1" );
        map0100.put( "120", "120_1" );
        map0100.put( "123", "123_1" );
        map0100.put( "124", "124_1" );
        map0100.put( "125", "125_1" );
        map0100.put( "126", "126_1" );
    }

    private static final Map<String, String> map0120_0121 = new HashMap<String, String>();
    static
    {
        map0120_0121.put( "48", "48_1" );
        map0120_0121.put( "60", "60_1" );
        map0120_0121.put( "61", "61_1" );
        map0120_0121.put( "63", "63_1" );
        map0120_0121.put( "120", "120_1" );
        map0120_0121.put( "122", "122_1" );
        map0120_0121.put( "123", "123_1" );
        map0120_0121.put( "124", "124_1" );
        map0120_0121.put( "125", "125_1" );
        map0120_0121.put( "126", "126_1" );
    }

    private static final Map<String, String> map0200p = new HashMap<String, String>();
    static
    {
        map0200p.put( "48", "48_1" );
        map0200p.put( "60", "60_1" );
        map0200p.put( "61", "61_1" );
        map0200p.put( "63", "63_1" );
        map0200p.put( "120", "120_1" );
        map0200p.put( "123", "123_1" );
        map0200p.put( "124", "124_1" );
        map0200p.put( "125", "125_1" );
    }

    private static final Map<String, String> map0220p_0221p = new HashMap<String, String>();
    static
    {
        map0220p_0221p.put( "48", "48_1" );
        map0220p_0221p.put( "60", "60_1" );
        map0220p_0221p.put( "61", "61_1" );
        map0220p_0221p.put( "63", "63_1" );
        map0220p_0221p.put( "120", "120_1" );
        map0220p_0221p.put( "122", "122_1" );
        map0220p_0221p.put( "123", "123_1" );
        map0220p_0221p.put( "124", "124_1" );
        map0220p_0221p.put( "125", "125_1" );
    }

    private static final Map<String, String> map0420p_0421p = new HashMap<String, String>();
    static
    {
        // map0420p_0421p.put( "39" , "39_1" );
        map0420p_0421p.put( "48", "48_1" );
        map0420p_0421p.put( "61", "61_2" );
        map0420p_0421p.put( "60", "60_1" );
        map0420p_0421p.put( "61", "61_1" );
        map0420p_0421p.put( "63", "63_1" );
        map0420p_0421p.put( "120", "120_1" );
        map0420p_0421p.put( "123", "123_1" );
        map0420p_0421p.put( "124", "124_1" );
        map0420p_0421p.put( "125", "125_1" );
        map0420p_0421p.put( "126", "126_1" );
    }

    private static final Map<String, String> map0300 = new HashMap<String, String>();
    static
    {
        map0300.put( "48", "48_2" );
        map0300.put( "60", "60_2" );
        map0300.put( "120", "120_3" );
        map0300.put( "121", "121_1" );
        map0300.put( "122", "122_2" );
        map0300.put( "126", "126_2" );
        map0300.put( "127", "127_1" );
    }

    public static final void validator( String pi, int[] fields, ISOMsg isoMsg ) throws Exception
    {
        String mti = isoMsg.getMTI();
        for( int i = 0; i < fields.length; i++ )
        {
            int field = fields[i];
            // checks for requestFormat
            String fieldKey = getFieldKey( mti, pi, field );
            RequestFormat requestFormat = map.get( fieldKey );

            String message = isoMsg.getString( field );
            // check for message length
            if ( requestFormat.getFixed() )
            {
                if ( mti.equals( "0300" ) && field == 120 )
                {
                    if ( message.length() != requestFormat.getMaxLength() && message.length() != 18 )
                    {
                        throw new Exception( RequestFormat.ErrorMsg.INVALID_LENGTH + " at field no. " + field );
                    }
                }
                else
                {
                    if ( message.length() != requestFormat.getMaxLength() )
                    {
                        throw new Exception( RequestFormat.ErrorMsg.INVALID_LENGTH + " at field no. " + field );
                    }
                }
            }
            else
            {
                if ( message.length() > requestFormat.getMaxLength() )
                {
                    throw new Exception( RequestFormat.ErrorMsg.INVALID_LENGTH + " at field no. " + field );
                }
            }

            // check for format
            if ( RequestFormat.StandardNotation.ALPHABETIC_NUMERIC.equals( requestFormat.getAttribute() ) )
            {
                if ( !isRegexMatch( message, RequestFormat.RegEx.ALPHABETIC_NUMERIC ) )
                {
                    throw new Exception( RequestFormat.ErrorMsg.INVALID_ALPHABETIC_NUMERIC + " at field no. " + field );
                }
            }
            if ( RequestFormat.StandardNotation.ALPHABETIC_NUMERIC_SPECIAL.equals( requestFormat.getAttribute() ) )
            {
                if ( !isRegexMatch( message, RequestFormat.RegEx.ALPHA_NUMERIC_SPECIAL) )
                {
                    throw new Exception( RequestFormat.ErrorMsg.INVALID_ALPHABETIC_NUMERIC_SPECIAL + " at field no. " + field );
                }
            }
            if ( RequestFormat.StandardNotation.CREDIT_DEBIT_NUMERIC.equals( requestFormat.getAttribute() ) )
            {
                if ( !isRegexMatch( message, RequestFormat.RegEx.CREDIT_DEBIT_AMOUNT ) )
                {
                    throw new Exception( RequestFormat.ErrorMsg.INVALID_AMOUNT + " at field no. " + field );
                }
            }
            if ( RequestFormat.StandardNotation.NUMERIC.equals( requestFormat.getAttribute() ) )
            {
                if ( !isRegexMatch( message, RequestFormat.RegEx.NUMERIC ) )
                {
                    throw new Exception( RequestFormat.ErrorMsg.INVALID_NUMERIC + " at field no. " + field );
                }
            }
            if ( RequestFormat.StandardNotation.AMOUNT.equals( requestFormat.getAttribute() ) )
            {
                if ( !isRegexMatch( message, RequestFormat.RegEx.AMOUNT ) )
                {
                    throw new Exception( RequestFormat.ErrorMsg.INVALID_AMOUNT + " at field no. " + field );
                }
            }
        }
    }

    public static boolean isRegexMatch( String txMessage, String regex )
    {
        Pattern p = Pattern.compile( regex );
        Matcher m = p.matcher( txMessage );
        boolean valid = m.find();

        return valid;
    }

    private static String getFieldKey( String mti, String pi, int field )
    {
        String fieldKey = String.valueOf( field );
        // 0200 atm and 0200 pos
        if ( mti.equals( "0200" ) )
        {
            if ( ProductIndicator.isATMMessage( pi ) )
            {
                if ( map0200a.containsKey( fieldKey ) )
                {
                    return map0200a.get( fieldKey );
                }
            }
            else // POS
            {
                if ( map0200p.containsKey( fieldKey ) )
                {
                    return map0200p.get( fieldKey );
                }
            }
        }
        // 0220/0221 atm and 0220/0221 pos
        else if ( mti.equals( "0220" ) || mti.equals( "0221" ) )
        {
            if ( ProductIndicator.isATMMessage( pi ) )
            {
                if ( map0220a_0221a.containsKey( fieldKey ) )
                {
                    return map0220a_0221a.get( fieldKey );
                }
            }
            else // POS
            {
                if ( map0220p_0221p.containsKey(fieldKey) )
                {
                    return map0220p_0221p.get(fieldKey);
                }
            }
        }
        // 0420/0421 atm and 0420/0421 pos
        else if ( mti.equals( "0420" ) || mti.equals( "0421" ) )
        {
            if ( ProductIndicator.isATMMessage( pi ) )
            {
                if ( map0420a_0421a.containsKey( fieldKey ) )
                {
                    return map0420a_0421a.get( fieldKey );
                }
            }
            else // POS
            {
                if ( map0420p_0421p.containsKey( fieldKey ) )
                {
                    return map0420p_0421p.get( fieldKey );
                }
            }
        }
        // 0100 authorization
        else if ( mti.equals( "0100" ) )
        {
            if ( map0100.containsKey( fieldKey ) )
            {
                return map0100.get( fieldKey );
            }
        }
        // 0120/0121 authorization
        else if ( mti.equals( "0120" ) || mti.equals( "0121" ) )
        {
            if ( map0120_0121.containsKey( fieldKey ) )
            {
                return map0120_0121.get( fieldKey );
            }
        }
        // 0300 FHM
        else if ( mti.equals( "0300" ) )
        {
            if ( map0300.containsKey( fieldKey ) )
            {
                return map0300.get( fieldKey );
            }
        }
        return fieldKey;
    }
}