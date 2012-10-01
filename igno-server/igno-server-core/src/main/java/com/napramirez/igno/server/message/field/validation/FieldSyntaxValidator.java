package com.napramirez.igno.server.message.field.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FieldSyntaxValidator - checks if the syntax of a data element complies to its definitions
 *
 * TODO: implement format checking
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public class FieldSyntaxValidator
    implements FieldValidator
{
    public void validate( Field field )
        throws FieldValidationException
    {
        FieldDefinition definition = field.getDefinition();

        if ( !isLengthValid( field, definition.isFixed() ) )
        {
            throw new FieldValidationException( field, "Invalid field length.  Actual: "
                + field.getStringValue().length() + ", Definition: " + field.getDefinition().getLength() + ", Fixed: "
                + field.getDefinition().isFixed() );
        }

        if ( !isFLIValid( field ) )
        {
            throw new FieldValidationException( field, "Field Length Indicator doesn't match the field length." );
        }

        if ( !isTypeValid( field ) )
        {
            throw new FieldValidationException( field, "Invalid data for type " + definition.getType().name() );
        }

        if ( !isFormatValid( field ) )
        {
            throw new FieldValidationException( field, "Invalid data for format '" + definition.getFormat() + "'" );
        }
    }

    private boolean isLengthValid( Field field, boolean fixed )
    {
        int fieldLength = field.getStringValue().length();
        int definitionLength = field.getDefinition().getLength();

        return fixed ? fieldLength == definitionLength : fieldLength <= definitionLength;
    }

    private boolean isFLIValid( Field field )
    {
        int fliLength = field.getDefinition().getFliLength();

        if ( fliLength > 0 )
        {
            String fieldValue = field.getStringValue().substring( fliLength );

            return field.getFLI() == fieldValue.length();
        }

        return true;
    }

    private boolean isTypeValid( Field field )
    {
        Pattern pattern = Pattern.compile( field.getDefinition().getType().toString() );
        Matcher matcher = pattern.matcher( field.getStringValue() );

        return matcher.find();
    }

    private boolean isFormatValid( Field field )
    {
        return true;
    }
}
