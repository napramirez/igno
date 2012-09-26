package com.napramirez.igno.server.message.field;

/**
 * TransactionFeeAmount - Field 028 in FIS ISO Specifications
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class TransactionFeeAmount
{
    public enum SignIndicator
    {
        INCENTIVE( " " ),
        SURCHARGE( "-" );

        private String code;

        private SignIndicator( String code )
        {
            this.code = code;
        }

        public String toString()
        {
            return code;
        }
    }

    private static final int FIELD_LENGTH = 9;

    private String signIndicator;

    private String amount;

    public TransactionFeeAmount( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Transaction Fee Amount field is invalid!" );
        }

        signIndicator = fieldStringValue.substring( 0, 1 );
        amount = fieldStringValue.substring( 1 );
    }

    public String getSignIndicator()
    {
        return signIndicator;
    }

    public void setSignIndicator( String signIndicator )
    {
        this.signIndicator = signIndicator;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount( String amount )
    {
        this.amount = amount;
    }
}
