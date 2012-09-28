package com.napramirez.igno.server.message.field.validation;

/**
 * FieldValidationException
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class FieldValidationException
    extends Exception
{
    private static final long serialVersionUID = -274487616108308559L;

    public FieldValidationException( Field field )
    {
        this( field, "" );
    }

    public FieldValidationException( String message )
    {
        super( message );
    }

    public FieldValidationException( Field field, String message )
    {
        super( "{ key: '" + field.getKey().toString() + "', value: '" + field.getStringValue() + "' }" + " " + message );
    }

    public FieldValidationException( Throwable throwable )
    {
        super( throwable );
    }

    public FieldValidationException( String message, Throwable throwable )
    {
        super( message, throwable );
    }
}
