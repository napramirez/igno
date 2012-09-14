package com.napramirez.igno.server.common.field;

/**
 * CardAcceptorLocation - Field 043 in FIS ISO Specifications
 *
 * @author <a href="mailto:napramirez@gmail.com"></a>
 */
public class CardAcceptorLocation
{
    private static final int FIELD_LENGTH = 40;

    private String terminalOwner;

    private String terminalCity;

    private String terminalState;

    private String terminalCountry;

    public CardAcceptorLocation( String fieldStringValue )
    {
        if ( fieldStringValue == null || fieldStringValue.length() != FIELD_LENGTH )
        {
            throw new IllegalArgumentException( "Card Acceptor Location field is invalid!" );
        }

        terminalOwner = fieldStringValue.substring( 0, 22 );
        terminalCity = fieldStringValue.substring( 22, 35 );
        terminalState = fieldStringValue.substring( 35, 38 );
        terminalCountry = fieldStringValue.substring( 38 );
    }

    public String getTerminalOwner()
    {
        return terminalOwner;
    }

    public void setTerminalOwner( String terminalOwner )
    {
        this.terminalOwner = terminalOwner;
    }

    public String getTerminalCity()
    {
        return terminalCity;
    }

    public void setTerminalCity( String terminalCity )
    {
        this.terminalCity = terminalCity;
    }

    public String getTerminalState()
    {
        return terminalState;
    }

    public void setTerminalState( String terminalState )
    {
        this.terminalState = terminalState;
    }

    public String getTerminalCountry()
    {
        return terminalCountry;
    }

    public void setTerminalCountry( String terminalCountry )
    {
        this.terminalCountry = terminalCountry;
    }
}
