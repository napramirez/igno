package com.napramirez.igno.server.message.field.validation;

/**
 * FieldDictionary
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public interface FieldDictionary
{
    FieldDefinition getDefinition( FieldKey key );
}
