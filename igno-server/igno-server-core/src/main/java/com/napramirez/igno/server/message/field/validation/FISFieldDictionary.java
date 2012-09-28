package com.napramirez.igno.server.message.field.validation;

import java.util.HashMap;
import java.util.Map;

import com.napramirez.igno.server.message.field.constants.ProductIndicator;

/**
 * FISFieldDictionary - holds the FIS ISO8583 data element specifications
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class FISFieldDictionary
    implements FieldDictionary
{
    private Map<FieldKey, FieldDefinition> map = new HashMap<FieldKey, FieldDefinition>();

    public FISFieldDictionary()
    {
        map.put( new FieldKey( 0 ), new FieldDefinition( 4, FieldType.NUMERIC, "Message Type" ) );
        map.put( new FieldKey( 1 ), new FieldDefinition( 16, FieldType.ALPHA_NUMERIC, "Secondary Bit Map" ) );
        map.put( new FieldKey( 2 ), new FieldDefinition( 19, false, FieldType.NUMERIC, "Primary Account Number (PAN)" ) );
        map.put( new FieldKey( 3 ), new FieldDefinition( 6, FieldType.NUMERIC, "Processing Code" ) );
        map.put( new FieldKey( 4 ), new FieldDefinition( 12, FieldType.NUMERIC, "Transaction Amount" ) );
        map.put( new FieldKey( 7 ), new FieldDefinition( 10, FieldType.NUMERIC, "Transmission Date and Time" ) );
        map.put( new FieldKey( 11 ), new FieldDefinition( 6, FieldType.NUMERIC, "Systems Trace Audit Number" ) );
        map.put( new FieldKey( 12 ), new FieldDefinition( 6, FieldType.NUMERIC, "Local Transaction Time" ) );
        map.put( new FieldKey( 13 ), new FieldDefinition( 4, FieldType.NUMERIC, "Local Transaction Date" ) );
        map.put( new FieldKey( 14 ), new FieldDefinition( 4, FieldType.NUMERIC, "Expiration Date" ) );
        map.put( new FieldKey( 15 ), new FieldDefinition( 4, FieldType.NUMERIC, "Settlement Date" ) );
        map.put( new FieldKey( 17 ), new FieldDefinition( 4, FieldType.NUMERIC, "Capture Date" ) );
        map.put( new FieldKey( 18 ), new FieldDefinition( 4, FieldType.NUMERIC, "Merchant Type" ) );
        map.put( new FieldKey( 22 ), new FieldDefinition( 3, FieldType.NUMERIC, "Point of Service Entry Mode" ) );
        map.put( new FieldKey( 23 ), new FieldDefinition( 3, FieldType.NUMERIC, "Card Sequence Number" ) );
        map.put( new FieldKey( 25 ), new FieldDefinition( 2, FieldType.NUMERIC, "Point of Service Condition Code" ) );
        map.put( new FieldKey( 27 ), new FieldDefinition( 1, FieldType.NUMERIC,
                                                          "Authorization Identification Response Length" ) );
        map.put( new FieldKey( 28 ), new FieldDefinition( 9, FieldType.AMOUNT, "Transaction Fee Amount" ) );
        map.put( new FieldKey( 32 ), new FieldDefinition( 11, false, FieldType.NUMERIC,
                                                          "Acquiring Institution Identification Code" ) );
        map.put( new FieldKey( 33 ), new FieldDefinition( 11, false, FieldType.NUMERIC,
                                                          "Forwarding Institution Identification Code" ) );
        map.put( new FieldKey( 35 ), new FieldDefinition( 37, false, FieldType.ALPHA_NUMERIC_SPECIAL, "Track 2 Data" ) );
        map.put( new FieldKey( 37 ), new FieldDefinition( 12, FieldType.ALPHA_NUMERIC, "Retrieval Reference Number" ) );
        map.put( new FieldKey( 38 ), new FieldDefinition( 6, FieldType.ALPHA_NUMERIC,
                                                          "Authorization Identification Response" ) );
        map.put( new FieldKey( 39 ), new FieldDefinition( 2, FieldType.ALPHA_NUMERIC, "Response Code" ) );
        map.put( new FieldKey( 41 ), new FieldDefinition( 16, FieldType.ALPHA_NUMERIC_SPECIAL,
                                                          "Card Acceptor Terminal Identification" ) );
        map.put( new FieldKey( 42 ), new FieldDefinition( 15, FieldType.ALPHA_NUMERIC_SPECIAL,
                                                          "Card Acceptor Identification Code" ) );
        map.put( new FieldKey( 43 ), new FieldDefinition( 40, FieldType.ALPHA_NUMERIC_SPECIAL,
                                                          "Card Acceptor Name/Location" ) );
        map.put( new FieldKey( 44, ProductIndicator.ATM_MESSAGE ),
                 new FieldDefinition( 27, 2, FieldType.ALPHA_NUMERIC_SPECIAL, "Additional Response Data (ATM)" ) );
        map.put( new FieldKey( 44, ProductIndicator.POS_MESSAGE ),
                 new FieldDefinition( 4, 2, FieldType.ALPHA_NUMERIC_SPECIAL, "Additional Response Data (POS)" ) );
        map.put( new FieldKey( 45 ), new FieldDefinition( 76, false, FieldType.ALPHA_NUMERIC_SPECIAL, "Track 1 Data" ) );
        map.put( new FieldKey( 48, ProductIndicator.ATM_MESSAGE ),
                 new FieldDefinition( 47, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Additional Data (ATM)" ) );
        map.put( new FieldKey( 48, ProductIndicator.POS_MESSAGE ),
                 new FieldDefinition( 30, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Retailer Data (POS)" ) );
        map.put( new FieldKey( 48, ProductIndicator.FROM_HOST_MAINTENANCE_MESSAGE ),
                 new FieldDefinition( 79, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Additional Data (FHM)" ) );
        map.put( new FieldKey( 49 ), new FieldDefinition( 3, FieldType.NUMERIC, "Transaction Currency Code" ) );
        map.put( new FieldKey( 52 ), new FieldDefinition( 16, FieldType.ALPHA_NUMERIC,
                                                          "Personal Identification Number (PIN) Data" ) );
        map.put( new FieldKey( 53 ), new FieldDefinition( 16, FieldType.NUMERIC,
                                                          "Security Related Control Information (Network Management)" ) );
        map.put( new FieldKey( 54 ), new FieldDefinition( 15, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Additional Amounts" ) );
        map.put( new FieldKey( 60, ProductIndicator.ATM_MESSAGE ),
                 new FieldDefinition( 15, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Terminal Data (ATM)" ) );
        map.put( new FieldKey( 60, ProductIndicator.POS_MESSAGE ),
                 new FieldDefinition( 19, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Terminal Data (POS)" ) );
        map.put( new FieldKey( 60, ProductIndicator.FROM_HOST_MAINTENANCE_MESSAGE ),
                 new FieldDefinition( 61, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "From Host Maintenance Data (FHM)" ) );
        map.put( new FieldKey( 61, ProductIndicator.ATM_MESSAGE ),
                 new FieldDefinition( 16, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Card Issuer and Authorizer Data (ATM)" ) );
        map.put( new FieldKey( 61, ProductIndicator.POS_MESSAGE ),
                 new FieldDefinition( 22, 3, FieldType.ALPHA_NUMERIC_SPECIAL,
                                      "Card Issuer-Category-Response Code (POS)" ) );
        map.put( new FieldKey( 62 ), new FieldDefinition( 13, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Postal Code" ) );
        map.put( new FieldKey( 63, ProductIndicator.ATM_MESSAGE ),
                 new FieldDefinition( 19, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "PIN Offset (ATM)" ) );
        map.put( new FieldKey( 63, ProductIndicator.POS_MESSAGE ),
                 new FieldDefinition( 600, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Additional Data (POS)" ) );
        map.put( new FieldKey( 64 ), new FieldDefinition( 16, FieldType.ALPHA_NUMERIC,
                                                          "Primary Message Authentication Code" ) );
        map.put( new FieldKey( 70 ), new FieldDefinition( 3, FieldType.NUMERIC, "Network Management Information Code" ) );
        map.put( new FieldKey( 73 ), new FieldDefinition( 6, FieldType.NUMERIC, "Action Date" ) );
        map.put( new FieldKey( 90 ), new FieldDefinition( 42, FieldType.NUMERIC, "Original Data Elements" ) );
        map.put( new FieldKey( 91 ), new FieldDefinition( 1, FieldType.ALPHA_NUMERIC, "File Update Code" ) );
        map.put( new FieldKey( 95 ), new FieldDefinition( 42, FieldType.ALPHA_NUMERIC, "Replacement Amounts" ) );
        map.put( new FieldKey( 100 ), new FieldDefinition( 11, false, FieldType.NUMERIC,
                                                           "Receiving Institution Identification Code" ) );
        map.put( new FieldKey( 101 ), new FieldDefinition( 4, 2, FieldType.ALPHA_NUMERIC_SPECIAL, "File Name" ) );
        map.put( new FieldKey( 102 ), new FieldDefinition( 30, 2, false, FieldType.ALPHA_NUMERIC_SPECIAL,
                                                           "Account Identification 1" ) );
        map.put( new FieldKey( 103 ), new FieldDefinition( 30, 2, false, FieldType.ALPHA_NUMERIC_SPECIAL,
                                                           "Account Identification 2" ) );
        map.put( new FieldKey( 120, ProductIndicator.ATM_MESSAGE ),
                 new FieldDefinition( 36, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Terminal Address-Branch-Region (ATM)" ) );
        map.put( new FieldKey( 120, ProductIndicator.POS_MESSAGE ),
                 new FieldDefinition( 32, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Terminal Name/Address-Branch (POS)" ) );
        map.put( new FieldKey( 120, ProductIndicator.NETWORK_MANAGEMENT_MESSAGE ),
                 new FieldDefinition( 9, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Key Management (Network Management)" ) );
        map.put( new FieldKey( 120, ProductIndicator.FROM_HOST_MAINTENANCE_MESSAGE, "c" ),
                 new FieldDefinition( 98, 3, FieldType.ALPHA_NUMERIC_SPECIAL,
                                      "Expanded Base Segment (File Update - CAF)" ) );
        map.put( new FieldKey( 120, ProductIndicator.FROM_HOST_MAINTENANCE_MESSAGE, "n" ),
                 new FieldDefinition( 18, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Base Segment (File Update - NEG)" ) );
        map.put( new FieldKey( 121 ), new FieldDefinition( 23, 3, FieldType.ALPHA_NUMERIC_SPECIAL,
                                                           "Authorization Indicators" ) );
        map.put( new FieldKey( 121, ProductIndicator.FROM_HOST_MAINTENANCE_MESSAGE ),
                 new FieldDefinition( 79, 3, FieldType.ALPHA_NUMERIC_SPECIAL,
                                      "Expanded ATM Segment Information (File Update - CAF)" ) );
        map.put( new FieldKey( 122, ProductIndicator.ATM_MESSAGE ),
                 new FieldDefinition( 14, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Multiple Account Indicator (ATM)" ) );
        map.put( new FieldKey( 122, ProductIndicator.FROM_HOST_MAINTENANCE_MESSAGE ),
                 new FieldDefinition( 120, 3, FieldType.ALPHA_NUMERIC_SPECIAL,
                                      "Expanded POS Segment Information (File Update - CAF)" ) );
        map.put( new FieldKey( 122, ProductIndicator.POS_MESSAGE ),
                 new FieldDefinition( 14, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Card Issuer ID Code (POS)" ) );
        map.put( new FieldKey( 123, ProductIndicator.NETWORK_MANAGEMENT_MESSAGE ),
                 new FieldDefinition( 553, 3, false, FieldType.ALPHA_NUMERIC_SPECIAL, "Cryptographic Service Message" ) );
        map.put( new FieldKey( 123, ProductIndicator.ATM_MESSAGE ), new FieldDefinition( 15, 3, FieldType.NUMERIC,
                                                                                         "Deposit Credit Amount (ATM)" ) );
        map.put( new FieldKey( 123, ProductIndicator.POS_MESSAGE ), new FieldDefinition( 23, 3, FieldType.NUMERIC,
                                                                                         "Invoice Data (POS)" ) );
        map.put( new FieldKey( 124, ProductIndicator.ATM_MESSAGE ),
                 new FieldDefinition( 4, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Depository Type (ATM)" ) );
        map.put( new FieldKey( 124, ProductIndicator.POS_MESSAGE ),
                 new FieldDefinition( 12, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Batch and Shift Data (POS)" ) );
        map.put( new FieldKey( 125, ProductIndicator.ATM_MESSAGE ),
                 new FieldDefinition( 4, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Account Indicator (ATM)" ) );
        map.put( new FieldKey( 125, ProductIndicator.ATM_MESSAGE, "a" ),
                 new FieldDefinition( 375, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Statement Print Data (ATM - Alternate)" ) );
        map.put( new FieldKey( 125, ProductIndicator.POS_MESSAGE ),
                 new FieldDefinition( 15, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Settlement Print Data (POS)" ) );
        map.put( new FieldKey( 126, ProductIndicator.ATM_MESSAGE ),
                 new FieldDefinition( 800, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "Additional Data (ATM)" ) );
        map.put( new FieldKey( 126, ProductIndicator.ATM_MESSAGE, "m" ),
                 new FieldDefinition( 157, 3, FieldType.ALPHA_NUMERIC_SPECIAL,
                                      "Additional Data (ATM - Multiple Account Select)" ) );
        map.put( new FieldKey( 126, ProductIndicator.POS_MESSAGE ),
                 new FieldDefinition( 41, 3, FieldType.ALPHA_NUMERIC_SPECIAL,
                                      "Preauthorization and Chargeback Data (POS)" ) );
        map.put( new FieldKey( 126, ProductIndicator.FROM_HOST_MAINTENANCE_MESSAGE ),
                 new FieldDefinition( 693, 3, false, FieldType.ALPHA_NUMERIC_SPECIAL,
                                      "Expanded Account Segment (File Update - CAF)" ) );
        map.put( new FieldKey( 127, ProductIndicator.ATM_MESSAGE ),
                 new FieldDefinition( 200, 3, false, FieldType.ALPHA_NUMERIC_SPECIAL, "User Data (ATM)" ) );
        map.put( new FieldKey( 127, ProductIndicator.ATM_MESSAGE, "b" ),
                 new FieldDefinition( 60, 3, false, FieldType.ALPHA_NUMERIC_SPECIAL,
                                      "User Data (ATM - Balance Inquiry Link Usage)" ) );
        map.put( new FieldKey( 127, ProductIndicator.ATM_MESSAGE, "m" ),
                 new FieldDefinition( 157, 3, FieldType.ALPHA_NUMERIC_SPECIAL,
                                      "User Data (ATM - Multiple Account Select)" ) );
        map.put( new FieldKey( 127, ProductIndicator.FROM_HOST_MAINTENANCE_MESSAGE ),
                 new FieldDefinition( 32, 3, FieldType.ALPHA_NUMERIC_SPECIAL, "AVS Data (File Update - CAF)" ) );
        map.put( new FieldKey( 127, ProductIndicator.POS_MESSAGE ),
                 new FieldDefinition( 60, false, FieldType.ALPHA_NUMERIC_SPECIAL, "User Data (POS)" ) );
        map.put( new FieldKey( 128 ), new FieldDefinition( 016, FieldType.ALPHA_NUMERIC,
                                                           "Secondary Message Authentication Code" ) );

    }

    public FieldDefinition getDefinition( FieldKey key )
    {
        FieldDefinition definition = map.get( key );
        if ( definition == null )
        {
            key.setSecondaryIndicator( null );
            definition = map.get( key );

            if ( definition == null )
            {
                key.setProductIndicator( null );
                definition = map.get( key );
            }
        }

        return definition;
    }
}
