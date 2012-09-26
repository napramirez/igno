package com.napramirez.igno.server.message.field.constants;

/**
 * ResponseCode - Field 039 in FIS ISO Specifications
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public final class ResponseCode
{
    public enum ATM
    {
        APPROVED( "00" ),
        EXTERNAL_DECLINE( "05" ),
        INVALID_TRANSACTION( "12" ),
        INVALID_AMOUNT_OR_INVALID_CASH_ADVANCE_AMOUNT_LIMIT( "13" ),
        INVALID_CARD_NUMBER( "14" ),
        FORMAT_ERROR( "30" ),
        EXPIRED_CARD_CAPTURE( "33" ),
        RESTRICTED_CARD_CAPTURE( "36" ),
        ALLOWABLE_PIN_TRIES_EXCEEDED_CAPTURE( "38" ),
        NO_CREDIT_ACCOUNT( "39" ),
        LOST_CARD( "41" ),
        NO_CHECKING_ACCOUNT( "52" ),
        NO_SAVINGS_ACCOUNT( "53" ),
        TRANSACTION_NOT_PERMITTED_TO_CARDHOLDER( "57" ),
        INVALID_CVC( "46" ),
        INVALID_CVC2( "47" ),
        INVALID_CVV( "48" ),
        INVALID_CVV2( "49" ),
        INVALID_CAVV( "50" ),
        INSUFFICIENT_FUNDS( "51" ),
        EXPIRED_CARD( "54" ),
        INVALID_PIN( "55" ),
        EXCEEDS_WITHRAWAL_FREQUENCY_LIMIT( "61" ),
        HARD_CAPTURE( "67" ),
        PIN_TRIES_EXCEEDED( "75" ),
        INELIGIBLE_AMOUNT( "76" ),
        NO_SHARING_ARRANGEMENT( "77" ),
        CONTACT_CARD_ISSUER( "78" ),
        NO_STATEMENT_INFORMATION_FOR_ACCOUNT( "86" ),
        STATEMENT_NOT_AVAILABLE_FOR_ACCOUNT( "87" ),
        SYSTEM_ERROR( "88" ),
        DATABASE_PROBLEM( "89" ),
        ISSUER_OR_SWITCH_INOPERATIVE( "91" ),
        FINANCIAL_INSTITUTION_OR_NETWORK_FACILITY_NOT_FOUND( "92" );

        private String code;

        ATM( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    public enum POS
    {
        APPROVED( "00" ),
        PLACE_CALL( "01" ),
        ISSUE_CALL( "02" ),
        INVALID_MERCHANT( "03" ),
        CAPTURE( "04" ),
        EXTERNAL_DECLINE( "05" ),
        ERROR( "06" ),
        CAPTURE_SPECIAL_CONDITION( "07" ),
        DUPLICATE_TRANSACTION( "09" ),
        INVALID_TRANSACTION( "12" ),
        INVALID_AMOUNT( "13" ),
        INVALID_CARD_NUMBER( "14" ),
        NO_SUCH_ISSUER( "15" ),
        FORMAT_ERROR( "30" ),
        BANK_NOT_SUPPORTED_BY_SWITCH( "31" ),
        EXPIRED_CARD_CAPTURE( "33" ),
        SUSPECTED_FRAUD_CAPTURE( "34" ),
        CARD_ACCEPTOR_CONTACT_ACQUIRER_CAPTURE( "35" ),
        RESTRICTED_CARD_CAPTURE( "36" ),
        CARD_ACCEPTOR_CALL_ACQUIRER_SECURITY( "37" ),
        ALLOWABLE_PIN_TRIES_EXCEEDED_CAPTURE( "38" ),
        DECLINE( "39" ),
        LOST_CARD_CAPTURE( "41" ),
        STOLEN_CARD_CAPTURE( "43" ),
        INVALID_CVC( "46" ),
        INVALID_CVC2( "47" ),
        INVALID_CVV( "48" ),
        INVALID_CVV2( "49" ),
        INVALID_CAVV( "50" ),
        INSUFFICIENT_FUNDS( "51" ),
        EXPIRED_CARD( "54" ),
        INVALID_PIN( "55" ),
        NO_CARD_RECORD( "56" ),
        TRANSACTION_NOT_PERMITTED( "57" ),
        TRANSACTION_NOT_PERMITTED_TO_TERMINAL( "58" ),
        EXCEEDS_WITHRAWAL_AMOUNT_LIMIT( "61" ),
        RESTRICTED_CARD( "62" ),
        EXCEEDS_WITHRAWAL_FREQUENCY_LIMIT( "65" ),
        RESPONSE_RECEIVED_TOO_LATE( "68" ),
        PIN_TRIES_EXCEEDED( "75" ),
        NO_SECURITY_MODULE( "82" ),
        NO_ACCOUNT( "83" ),
        NO_PBF( "84" ),
        PBF_UPDATE_ERROR( "85" ),
        INVALID_AUTHORIZATION_TYPE( "86" ),
        BAD_TRACK_DATA( "87" ),
        PTLF_ERROR( "88" ),
        INVALID_ROUTE_SERVICE( "89" ),
        CUTOFF_IN_PROCESS( "90" ),
        ISSUER_OR_SWITCH_INOPERATIVE( "91" ),
        FINANCIAL_INSTITUTION_OR_NETWORK_FACILITY_NOT_FOUND( "92" ),
        DUPLICATE_TRANSACTION2( "94" ),
        SYSTEM_MALFUNCTION( "95" ),
        UNABLE_TO_AUTHORIZE( "N0" ),
        INVALID_PAN_LENGTH( "N1" ),
        PREAUTHORIZATION_FULL( "N2" ),
        MAXIMUM_ONLINE_REFUND_REACHED( "N3" ),
        MAXIMUM_OFFLINE_REFUND_REACHED( "N4" ),
        MAXIMUM_CREDIT_PER_REFUND( "N5" ),
        MAXIMUM_REFUND_CREDIT_REACHED( "N6" ),
        CUSTOMER_SELECTED_NEGATIVE_FILE_REASON( "N7" ),
        OVER_FLOOR_LIMIT( "N8" ),
        MAXIMUM_NUMBER_REFUND_CREDITS( "N9" ),
        REFERRAL_FILE_FULL( "O0" ),
        NEG_FILE_PROBLEM( "O1" ),
        ADVANCE_LESS_THAN_MINIMUM( "O2" ),
        DELINQUENT( "O3" ),
        OVER_LIMIT_TABLE( "O4" ),
        PIN_REQUIRED( "O5" ),
        MOD_10_CHECK( "O6" ),
        FORCE_POST( "O7" ),
        BAD_PBF( "O8" ),
        NEG_FILE_PROBLEM2( "O9" ),
        CAF_PROBLEM( "P0" ),
        OVER_DAILY_LIMIT( "P1" ),
        CAPF_NOT_FOUND( "P2" ),
        ADVANCE_LESS_THAN_MINIMUM2( "P3" ),
        NUMBER_OF_TIMES_USED( "P4" ),
        DELINQUENT2( "P5" ),
        OVER_LIMIT_TABLE2( "P6" ),
        ADVANCE_LESS_THAN_MINIMUM3( "P7" ),
        ADMINISTRATIVE_CARD_NEEDED( "P8" ),
        ENTER_LESSER_AMOUNT( "P9" ),
        INVALID_TRANSACTION_DATE( "Q0" ),
        INVALID_EXPIRATION_DATE( "Q1" ),
        INVALID_TRANSACTION_CODE( "Q2" ),
        ADVANCE_LESS_THAN_MINIMUM4( "Q3" ),
        NUMBER_OF_TIMES_USED2( "Q4" ),
        DELINQUENT3( "Q5" ),
        OVER_LIMIT_TABLE3( "Q6" ),
        AMOUNT_OVER_MAXIMUM( "Q7" ),
        ADMINISTRATIVE_CARD_NOT_FOUND( "Q8" ),
        ADMINISTRATIVE_CARD_NOT_ALLOWED( "Q9" ),
        APPROVED_ADMINISTRATIVE_REQUEST_PERFORMED_IN_WINDOW( "R0" ),
        APPROVED_ADMINISTRATIVE_REQUEST_PERFORMED_OUT_WINDOW( "R1" ),
        APPROVED_ADMINISTRATIVE_REQUEST_PERFORMED_ANYTIME( "R2" ),
        CHARGEBACK_CUSTOMER_FILE_UPDATED( "R3" ),
        CHARGEBACK_CUSTOMER_FILE_UPDATED_ACQUIRER_NOT_FOUND( "R4" ),
        CHARGEBACK_INCORRECT_PREFIX_NUMBER( "R5" ),
        CHARGEBACK_INCORRECT_RESPONSE_CODE_OR_CPF( "R6" ),
        ADMINISTRATIVE_TRANSACTIONS_NOT_SUPPORTED( "R7" ),
        CARD_ON_NATIONAL_NEGATIVE_FILE( "R8" ),
        PTLF_FULL( "S4" ),
        CHARGEBACK_APPROVED_CUSTOMER_FILE_NOT_UPDATED( "S5" ),
        CHARGEBACK_APPROVED_CUSTOMER_FILE_NOT_UPDATED_ACQUIRER_NOT_FOUND( "S6" ),
        CHARGEBACK_ACCEPTED_INCORRECT_DESTINATION( "S7" ),
        ADMN_FILE_PROBLEM( "S8" ),
        UNABLE_TO_VALIDATE_PIN_SECURITY_MODULE_DOWN( "S9" ),
        INVALID_CREDIT_CARD_ADVANCE_AMOUNT( "T1" ),
        INVALID_TRANSACTION_DATE2( "T2" ),
        CARD_NOT_SUPPORTED( "T3" ),
        AMOUNT_OVER_MAXIMUM2( "T4" ),
        CAF_STATUS_ZERO_OR_NINE( "T5" ),
        BAD_UAF( "T6" ),
        CASH_BACK_EXCEEDS_DAILY_LIMIT( "T7" );

        private String code;

        POS( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    public enum REVERSAL
    {
        APPROVED( "00" ),
        CUSTOMER_CANCELLATION( "17" ),
        INVALID_RESPONSE( "20" ),
        SUSPECTED_MALFUNCTION_ATM_ONLY( "21" ),
        SUSPECTED_MALFUNCTION( "22" ),
        COMPLETED_PARTIALLY_ATM_ONLY( "32" ),
        REQUEST_FUNCTION_NOT_SUPPORTED_POS_ONLY( "40" ),
        RESPONSE_RECEIVED_TOO_LATE( "68" ),
        DESTINATION_NOT_AVAILABLE( "82" ),
        SUSPECT_REVERSAL( "96" ),
        MAC_FAILURE( "U1" ),
        KMAC_SYNCHRONIZATION_ERROR( "U2" ),
        MESSAGE_REPLAY_ERROR( "U3" ),
        INVALID_MAC( "U4" ),
        KME_SYNCHRONIZATION_ERROR( "U5" );

        private String code;

        REVERSAL( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    public enum ATM_ADJUSTMENT
    {
        ATM_MALFUNCTION( "22" ),
        ORIGINAL_AMOUNT_INCORRECT( "64" ),
        SUSPICIOUS_REVERSAL( "83" ),
        MISDISPENSE_REVERSAL( "84" ),
        PLUS_ADD_CASH_WITHDRAWAL( "85" ),
        DUPLICATE_TRANSMISSION( "94" ),
        RECONCILIATION_ERROR( "95" );

        private String code;

        ATM_ADJUSTMENT( String code )
        {
            this.code = code;
        }

        public String toString()

        {
            return code;
        }
    }

    public enum POS_ADJUSTMENT
    {
        APPROVED( "00" ),
        ORIGINAL_AMOUNT_INCORRECT( "64" ),
        DUPLICATE_TRANSMISSION( "94" ),
        RECONCILE_ERROR( "95" ),
        SYSTEM_MALFUNCTION( "96" ),
        SUSPICIOUS_REVERSAL_OVERRIDE( "S1" ),
        MISDISPENSE_REVERSAL_OVERRIDE( "S2" ),
        PLUS_ADD_CASH_WITHDRAWAL_OR_ADVANCE( "S3" );

        private String code;

        POS_ADJUSTMENT( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }
}
