package com.napramirez.igno.server.message.field.validation;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;

import com.napramirez.igno.server.message.field.constants.ProductIndicator;

/**
 * FieldKey - a unique reference to a data element in the FIS implementation of the ISO8583 specification. Consists of
 * the 3-digit field index and product indicator suffix. A secondary indicator suffix may also be present.
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class FieldKey
{
    private static final int FIELD_INDEX_LENGTH = 3;

    private static final char PADDING_CHAR_LEFT = '0';

    private int fieldIndex;

    private ProductIndicator productIndicator;

    private String secondaryIndicator;

    public FieldKey( int fieldIndex )
    {
        this( fieldIndex, null, null );
    }

    public FieldKey( int fieldIndex, ProductIndicator productIndicator )
    {
        this( fieldIndex, productIndicator, null );
    }

    public FieldKey( int fieldIndex, ProductIndicator productIndicator, String secondaryIndicator )
    {
        this.fieldIndex = fieldIndex;
        this.productIndicator = productIndicator;
        this.secondaryIndicator = secondaryIndicator;
    }

    public int getFieldIndex()
    {
        return fieldIndex;
    }

    public void setFieldIndex( int fieldIndex )
    {
        this.fieldIndex = fieldIndex;
    }

    public ProductIndicator getProductIndicator()
    {
        return productIndicator;
    }

    public void setProductIndicator( ProductIndicator productIndicator )
    {
        this.productIndicator = productIndicator;
    }

    public String getSecondaryIndicator()
    {
        return secondaryIndicator;
    }

    public void setSecondaryIndicator( String secondaryIndicator )
    {
        this.secondaryIndicator = secondaryIndicator;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append( getFieldIndexString() );

        if ( productIndicator != null )
        {
            sb.append( productIndicator.getSuffix() );

            if ( secondaryIndicator != null && secondaryIndicator.trim().length() > 0 )
            {
                sb.append( secondaryIndicator );
            }
        }

        return sb.toString();
    }

    public String getFieldIndexWithProductIndicator()
    {
        StringBuilder sb = new StringBuilder();
        sb.append( getFieldIndexString() );

        if ( productIndicator != null )
        {
            sb.append( productIndicator.getSuffix() );
        }

        return sb.toString();
    }

    public String getFieldIndexString()
    {
        try
        {
            return ISOUtil.padleft( Integer.toString( fieldIndex ), FIELD_INDEX_LENGTH, PADDING_CHAR_LEFT );
        }
        catch ( ISOException e )
        {
            return null;
        }
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( null == obj )
        {
            return false;
        }

        if ( !getClass().equals( obj.getClass() ) )
        {
            return false;
        }

        if ( this == obj )
        {
            return true;
        }

        return toString().equals( obj.toString() );
    }

    @Override
    public int hashCode()
    {
        if ( toString() == null )
        {
            return super.hashCode();
        }
        else
        {
            return new HashCodeBuilder( 7, 17 ).append( toString() ).toHashCode();
        }
    }
}
