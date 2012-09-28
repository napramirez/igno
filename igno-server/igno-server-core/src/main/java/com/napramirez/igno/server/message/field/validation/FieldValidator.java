package com.napramirez.igno.server.message.field.validation;

/**
 * FieldValidator
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public interface FieldValidator
{
    void validate( Field field )
        throws FieldValidationException;
}
