package com.napramirez.igno.server.message.field.validation;

/**
 * Field - represents a data element in compliance to the FIS ISO8583 specifications
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class Field
{
    private FieldKey key;

    private FieldDefinition definition;

    private String stringValue;

    public Field( FieldKey key, FieldDefinition definition, String stringValue )
    {
        this.key = key;
        this.definition = definition;
        this.stringValue = stringValue;
    }

    public int getFLI()
    {
        int fli = 0;
        int fliLength = definition.getFliLength();

        try
        {
            fli = Integer.parseInt( stringValue.substring( 0, fliLength ) );
        }
        catch ( NumberFormatException e )
        {
        }

        return fli;
    }

    public FieldKey getKey()
    {
        return key;
    }

    public void setKey( FieldKey key )
    {
        this.key = key;
    }

    public FieldDefinition getDefinition()
    {
        return definition;
    }

    public void setDefinition( FieldDefinition definition )
    {
        this.definition = definition;
    }

    public String getStringValue()
    {
        return stringValue;
    }

    public void setStringValue( String stringValue )
    {
        this.stringValue = stringValue;
    }
}
