package com.napramirez.igno.common.message;

import java.io.IOException;
import java.io.InputStream;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.BASE24Packager;
import org.jpos.iso.packager.XMLPackager;

/**
 * MessageTemplateFactory - creates a pre-populated ISOMsg as defined in the FIS ISO8583 specifications
 *
 * @author <a href="mailto:napramirez@gmail.com">Nap Ramirez</a>
 */
public enum MessageTemplateFactory
{
    INSTANCE;

    /**
     * Takes in a MessageTemplate as input, to identify which pre-set templates to create.
     * 
     * @param template
     * @return pre-populated ISOMsg, null otherwise
     */
    public ISOMsg getMessageTemplate( MessageTemplate template )
    {
        String templatePath = "messages/request/" + template;
        InputStream is = MessageTemplateFactory.class.getClassLoader().getResourceAsStream( templatePath );

        try
        {
            ISOMsg messageTemplate = new ISOMsg();
            messageTemplate.setPackager( new XMLPackager() );
            messageTemplate.unpack( is );
            messageTemplate.setPackager( new BASE24Packager() );

            is.close();

            return messageTemplate;
        }
        catch ( IOException e )
        {
            return null;
        }
        catch ( ISOException e )
        {
            return null;
        }
    }
}
