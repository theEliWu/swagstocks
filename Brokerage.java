import java.lang.reflect.*;
import java.util.*;


/**
 * Represents a brokerage.
 */
public class Brokerage implements Login
{
    private Map<String, Trader> traders;

    private Set<Trader> loggedTraders;

    private StockExchange exchange;


    public Brokerage( StockExchange exchange )
    {
        this.exchange = exchange;
        traders = new TreeMap<String, Trader>();
        loggedTraders = new TreeSet<Trader>();
    }


    public int addUser( String name, String psw )
    {
        if ( name.length() < 4 || name.length() > 10 )
        {
            return -1;
        }
        else if ( psw.length() < 2 || psw.length() > 10 )
        {
            return -2;
        }
        else if ( traders.containsKey( name ) )
        {
            return -3;
        }
        else
        {
            Trader tradeUser = new Trader( this, name, psw );
            traders.put( name, tradeUser );
            return 0;
        }
    }


    public void getQuote( String symbol, Trader trader )
    {
        trader.receiveMessage( exchange.getQuote( symbol ) );
    }


    public int login( String name, String psw )
    {
        Trader temp = traders.get( name );

        if ( !traders.containsKey( name ) )
        {
            return -1;
        }
        else if ( !temp.getPassword().equals( psw ) )
        {
            return -2;
        }
        else if ( loggedTraders.contains( temp ) )
        {
            return -3;

        }
        else
        {
            temp.receiveMessage( "Welcome to SafeTrade!" );
            temp.openWindow();
            loggedTraders.add( temp );
            return 0;
        }
    }


    public void logout( Trader trader )
    {
        loggedTraders.remove( trader );
    }


    public void placeOrder( TradeOrder order )
    {
        exchange.placeOrder( order );
    }


    //
    // The following are for test purposes only
    //
    protected Map<String, Trader> getTraders()
    {
        return traders;
    }


    protected Set<Trader> getLoggedTraders()
    {
        return loggedTraders;
    }


    protected StockExchange getExchange()
    {
        return exchange;
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Brokerage.
     */
    public String toString()
    {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for ( Field field : fields )
        {
            try
            {
                str += separator + field.getType().getName() + " "
                    + field.getName() + ":" + field.get( this );
            }
            catch ( IllegalAccessException ex )
            {
                System.out.println( ex );
            }

            separator = ", ";
        }

        return str + "]";
    }
}
