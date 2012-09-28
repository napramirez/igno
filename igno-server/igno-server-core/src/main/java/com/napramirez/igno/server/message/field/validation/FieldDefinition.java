package com.napramirez.igno.server.message.field.validation;

/**
 * FieldDefinition - describes a data element in compliance to the FIS ISO8583 specifications
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class FieldDefinition
{
    private int length;

    private int fliLength;

    private boolean fixed;

    private String name;

    private FieldType type;

    private String format;

    public FieldDefinition( int length, FieldType type )
    {
        this( length, 0, true, type, null );
    }

    public FieldDefinition( int length, FieldType type, String name )
    {
        this( length, 0, true, type, name );
    }

    public FieldDefinition( int length, boolean fixed, FieldType type, String name )
    {
        this( length, 0, fixed, type, name, null );
    }

    public FieldDefinition( int length, int fliLength, boolean fixed, FieldType type )
    {
        this( length, fliLength, fixed, type, null );
    }

    public FieldDefinition( int length, int fliLength, FieldType type, String name )
    {
        this( length, fliLength, true, type, name, null );
    }

    public FieldDefinition( int length, int fliLength, boolean fixed, FieldType type, String name )
    {
        this( length, fliLength, fixed, type, name, null );
    }

    public FieldDefinition( int length, int fliLength, boolean fixed, FieldType type, String name, String format )
    {
        this.length = length;
        this.fliLength = fliLength;
        this.fixed = fixed;
        this.name = name;
        this.type = type;
        this.format = format;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength( int length )
    {
        this.length = length;
    }

    public int getFliLength()
    {
        return fliLength;
    }

    public void setFliLength( int fliLength )
    {
        this.fliLength = fliLength;
    }

    public boolean isFixed()
    {
        return fixed;
    }

    public void setFixed( boolean fixed )
    {
        this.fixed = fixed;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public FieldType getType()
    {
        return type;
    }

    public void setType( FieldType type )
    {
        this.type = type;
    }

    public String getFormat()
    {
        return format;
    }

    public void setFormat( String format )
    {
        this.format = format;
    }
}
