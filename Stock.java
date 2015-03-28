import java.util.*;
import java.lang.reflect.*;
import java.text.DecimalFormat;


/**
 * Represents a stock in the SafeTrade project
 */
public class Stock
{
    public static DecimalFormat money = new DecimalFormat( "0.00" );

    private String stockSymbol;

    private String companyName;

    private double loPrice, hiPrice, lastPrice;

    private int volume;

    private PriorityQueue<TradeOrder> buyOrders, sellOrders;


    public Stock( String symbol, String name, double price )
    {
        stockSymbol = symbol;
        companyName = name;
        lastPrice = price;
        loPrice = price;
        hiPrice = price;
        volume = 0;
        buyOrders = new PriorityQueue<TradeOrder>( 1,
                        new PriceComparator( true ) );
        sellOrders = new PriorityQueue<TradeOrder>( 1,
                        new PriceComparator( false ) );
    }


    protected void executeOrders()
    {
        while ( !buyOrders.isEmpty() && !sellOrders.isEmpty() )
        {
            TradeOrder topBuy = buyOrders.peek();
            TradeOrder topSell = sellOrders.peek();

            double tradePrice;
            if ( topBuy.isLimit() && topSell.isLimit()
                            && topBuy.getPrice() <= topSell.getPrice() )
            {
                tradePrice = topSell.getPrice();
            }
            else if ( topBuy.isLimit() && topSell.isMarket() )
            {
                tradePrice = topBuy.getPrice();
            }
            else if ( topBuy.isMarket() && topSell.isLimit() )
            {
                tradePrice = topSell.getPrice();
            }
            else
            {
                tradePrice = lastPrice;
            }
            int tradeShares = Math.min( topBuy.getShares(), topSell.getShares() );
            topBuy.subtractShares( tradeShares );
            topSell.subtractShares( tradeShares );
            if ( topBuy.getShares() == 0 )
                buyOrders.remove();
            if ( topSell.getShares() == 0 )
                sellOrders.remove();

            if ( tradePrice > hiPrice )
                hiPrice = tradePrice;
            if ( tradePrice < loPrice )
                loPrice = tradePrice;
            lastPrice = tradePrice;
            volume += tradeShares;
            double amt = tradeShares * tradePrice;
            topBuy.getTrader().receiveMessage( "You bought: " + tradeShares
                + " at " + money.format( tradePrice ) + " amt "
                + money.format( amt ) );
            topSell.getTrader().receiveMessage( "You sold: " + tradeShares
                + " at " + money.format( tradePrice ) + " amt "
                + money.format( amt ) );
            
        }
    }

    public String getQuote()
    {
        String msg = companyName + " (" + stockSymbol + ")\nPrice: "
                        + lastPrice + "  hi: " + hiPrice + "  lo: " + loPrice + "  vol: "
                        + volume + "\nAsk: ";
        if ( !sellOrders.isEmpty() )
        {
            TradeOrder smallSell = null;
            for ( TradeOrder a : sellOrders )
            {
                if ( a.isLimit()
                                && ( smallSell == null || a.getPrice() < smallSell.getPrice() ) )
                {
                    smallSell = a;
                }
                else if ( smallSell == null || lastPrice < smallSell.getPrice() )
                {
                    smallSell = a;
                }
            }
            if ( smallSell.isLimit() )
            {
                msg = msg.concat( smallSell.getPrice() + " " );
            }
            else
            {
                msg = msg.concat( lastPrice + " " );
            }
            msg = msg.concat( "size: " + smallSell.getShares() + "  Bid: " );
        }
        else
        {
            msg = msg.concat( "none  Bid: " );
        }

        if ( !buyOrders.isEmpty() )
        {
            TradeOrder largeBuy = null;
            for ( TradeOrder a : buyOrders )
            {
                if ( a.isLimit()
                                && ( largeBuy == null || a.getPrice() > largeBuy.getPrice() ) )
                {
                    largeBuy = a;
                }
                else if ( largeBuy == null || lastPrice > largeBuy.getPrice() )
                {
                    largeBuy = a;
                }
            }
            if ( largeBuy.isLimit() )
            {
                msg = msg.concat( largeBuy.getPrice() + " " );
            }
            else
            {
                msg = msg.concat( lastPrice + " " );
            }
            msg = msg.concat( "size: " + largeBuy.getShares() );
        }
        else
        {
            msg = msg.concat( "none" );
        }
        return msg;
    }


    public void placeOrder( TradeOrder order )
    {
        String msg = "New Order:  ";
        if ( order.isBuy() )
        {
            buyOrders.add( order );
            msg = msg.concat( "Buy " );
        }
        else
        {
            sellOrders.add( order );
            msg = msg.concat( "Sell " );
        }
        msg = msg.concat( order.getSymbol() + " (" + companyName + ")\n"
                        + order.getShares() + " shares at " );
        if ( order.isMarket() )
        {
            msg = msg.concat( "market" );
        }
        else
        {
            msg = msg.concat( Double.toString( order.getPrice() ) );
        }

        order.getTrader().receiveMessage( msg );
        executeOrders();
    }


    //
    // The following are for test purposes only
    //

    protected String getStockSymbol()
    {
        return stockSymbol;
    }


    protected String getCompanyName()
    {
        return companyName;
    }


    protected double getLoPrice()
    {
        return loPrice;
    }


    protected double getHiPrice()
    {
        return hiPrice;
    }


    protected double getLastPrice()
    {
        return lastPrice;
    }


    protected int getVolume()
    {
        return volume;
    }


    protected PriorityQueue<TradeOrder> getBuyOrders()
    {
        return buyOrders;
    }


    protected PriorityQueue<TradeOrder> getSellOrders()
    {
        return sellOrders;
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Stock.
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
