package com.napramirez.igno.server.common.constants;

/**
 * ProcessingCode - Field 003 in FIS ISO Specifications
 * 
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public final class ProcessingCode
{
    /**
     * TransactionCode - Position 1-2
     */
    public enum TransactionCode
    {
        GOODS_AND_SERVICES( "00" ),
        WITHDRAWAL_OR_CASH_ADVANCE( "01" ),
        DEBIT_ADJUSTMENT( "02" ),
        CHECK_FUNDS_GUARANTEE( "03" ),
        CHECK_VERIFICATION( "04" ),
        GOODS_AND_SERVICES_WITH_CASHBACK( "09" ),
        ADJUSTMENT_CASH_ADVANCE( "14" ),
        ADJUSTMENT_PURCHASE_WITH_CASHBACK( "19" ),
        RETURNS( "20" ),
        DEPOSIT( "21" ),
        CREDIT_ADJUSTMENT( "22" ),
        LOAD_TO_STORED_VALUE_CARD( "28" ),
        BALANCE_INQUIRY( "31" ),
        CARDHOLDER_ACCOUNTS_TRANSFER( "40" ),
        UNLOAD_FROM_STORED_VALUE_CARD( "48" ),
        MAIL_PHONE_ORDER( "80" ),
        CARD_VERIFICATION( "81" ),
        PAYMENT_ENCLOSED( "90" ),
        MESSAGE_TO_FINANCIAL_INSTITUTION( "91" ),
        CHECK_CASH( "92" ),
        LOG_ONLY( "93" ),
        STATEMENT_PRINT( "94" ),
        MINI_STATEMENT( "95" ),
        PIN_CHANGE( "96" ),
        CARD_ACTIVATION( "98" );
        
        private String code;
        
        TransactionCode( String code )
        {
            this.code = code;
        }
        
        public String toString()
        {
            return code;
        }
    }
    
    /**
     * AccountType - Position 3-4 and Position 5-6
     */
    public enum AccountType
    {
        NO_ACCOUNT_SPECIFIED( "00" ),
        SAVINGS_ACCOUNT_TYPE( "10" ),
        CHECK_ACCOUNT_TYPE( "20" ),
        CREDIT_ACCOUNT_TYPE( "30" );
        
        private String code;
        
        AccountType( String code )
        {
            this.code = code;
        }
        
        public String toString()
        {
            return code;
        }
    }
}
