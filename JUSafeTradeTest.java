import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.regex.*;

import org.junit.*;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;


/**
 * SafeTrade tests: TradeOrder PriceComparator Trader Brokerage StockExchange
 * Stock
 *
 * @author Michael Shieh
 * @author TODO Name of group member
 * @author TODO Name of group member
 * @version TODO date
 * @author Assignment: JM Chapter 19 - SafeTrade
 * 
 * @author Sources: TODO sources
 *
 */
public class JUSafeTradeTest
{
    // --Test TradeOrder
    /**
     * TradeOrder tests: TradeOrderConstructor - constructs TradeOrder and then
     * compare toString TradeOrderGetTrader - compares value returned to
     * constructed value TradeOrderGetSymbol - compares value returned to
     * constructed value TradeOrderIsBuy - compares value returned to
     * constructed value TradeOrderIsSell - compares value returned to
     * constructed value TradeOrderIsMarket - compares value returned to
     * constructed value TradeOrderIsLimit - compares value returned to
     * constructed value TradeOrderGetShares - compares value returned to
     * constructed value TradeOrderGetPrice - compares value returned to
     * constructed value TradeOrderSubtractShares - subtracts known value &
     * compares result returned by getShares to expected value
     */
    private String symbol = "GGGL";

    private boolean buyOrder = true;

    private boolean marketOrder = true;

    private int numShares = 123;

    private int numToSubtract = 24;

    private double price = 123.45;


    @Test
    public void TradeOConst()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        String toStr = to.toString();

        assertTrue( "<< Invalid TradeOrder Constructor >>",
            toStr.contains( "TradeOrder[Trader trader:null" )
                && toStr.contains( "java.lang.String symbol:" + symbol )
                && toStr.contains( "boolean buyOrder:" + buyOrder )
                && toStr.contains( "boolean marketOrder:" + marketOrder )
                && toStr.contains( "int numShares:" + numShares )
                && toStr.contains( "double price:" + price ) );
    }


    @Test
    public void TradeOGetPrice()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertEquals( "<< TradeOrder: " + to.getPrice() + " should be " + price
            + ">>", price, to.getPrice(), 0.0 );
    }


    @Test
    public void TradeOGetShares()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertTrue( "<< TradeOrder: " + to.getShares() + " should be "
            + numShares + ">>", numShares == to.getShares()
            || ( numShares - numToSubtract ) == to.getShares() );
    }


    @Test
    public void TradeOGetSymb()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertEquals( "<< TradeOrder: " + to.getTrader() + " should be "
            + symbol + " >>", symbol, to.getSymbol() );
    }


    @Test
    public void TradeOGetTrader()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertNull( "<< TradeOrder: " + to.getTrader() + " should be null >>",
            to.getTrader() );
    }


    @Test
    public void TradeOToString()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertNotNull( to.toString() );
    }


    @Test
    public void tradeOIsBuy()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );

        assertTrue( "<< TradeOrder: " + to.isBuy() + " should be " + buyOrder
            + " >>", to.isBuy() );
    }


    @Test
    public void tradeOIsLimit()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );

        assertFalse( "<< TradeOrder: " + to.isLimit() + " should be "
            + !marketOrder + ">>", to.isLimit() );
    }


    @Test
    public void tradeOIsSell()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertFalse( "<< TradeOrder: " + to.isSell() + " should be "
            + !buyOrder + " >>", to.isSell() );
    }


    @Test
    public void tradeOIsMarket()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertTrue( "<< TradeOrder: " + to.isMarket() + " should be "
            + marketOrder + " >>", to.isMarket() );
    }


    @Test
    public void tradeOSubtractShares()
    {
        TradeOrder to = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        to.subtractShares( numToSubtract );
        assertEquals( "<< TradeOrder: subtractShares(" + numToSubtract
            + ") should be " + ( numShares - numToSubtract ) + ">>", numShares
            - numToSubtract, to.getShares() );
    }


    // --Test TraderWindow Stub
    @Test
    public void traderWinConst()
    {
        TraderWindow tw = new TraderWindow( new Trader( new Brokerage( new StockExchange() ),
            "tester",
            "check" ) );
        assertNotNull( tw );
    }


    @Test
    public void traderWinShowMsg()
    {
        TraderWindow tw = new TraderWindow( new Trader( new Brokerage( new StockExchange() ),
            "tester",
            "check" ) );
        assertNotNull( tw );
        tw.showMessage( null );
    }


    // --Test PriceComparator

    @Test
    public void priceCompare()
    {
        PriceComparator t = new PriceComparator();

        TradeOrder exq = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );

        TradeOrder exq2 = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );

        assertNotNull( t );
        assertNotNull( exq );
        assertNotNull( exq2 );
        assertEquals( t.compare( exq, exq2 ), 0 );
    }


    // --Test Trader
    @Test
    public void traderCompare()
    {
        Trader t = new Trader( new Brokerage( new StockExchange() ),
            "tester",
            "check" );
        Trader r = new Trader( new Brokerage( new StockExchange() ),
            "tester",
            "check" );
        assertEquals( t.compareTo( r ), 0 );

        r = new Trader( new Brokerage( new StockExchange() ),
            "Atester",
            "check" );
        assertEquals( t.compareTo( r ), 19 );

        r = new Trader( new Brokerage( new StockExchange() ),
            "Ztester",
            "check" );
        assertEquals( t.compareTo( r ), -6 );
    }


    @Test
    public void traderEquals()
    {
        Trader t = new Trader( new Brokerage( new StockExchange() ),
            "tester",
            "check" );
        Trader r = new Trader( new Brokerage( new StockExchange() ),
            "tester",
            "check" );
        assertTrue( t.equals( r ) );
    }


    @Test
    public void traderGetQuote()
    {
        Trader t = new Trader( new Brokerage( new StockExchange() ),
            "tester",
            "check" );
        t.getQuote( symbol );
        assertTrue( !t.mailbox().isEmpty() );
    }


    @Test
    public void traderWindow()
    {
        Trader t = new Trader( new Brokerage( new StockExchange() ),
            "tester",
            "check" );
        t.openWindow();
        assertNotNull( t.toString() );
    }


    @Test
    public void traderPlaceOrder()
    {
        Trader t = new Trader( new Brokerage( new StockExchange() ),
            "tester",
            "check" );
        t.placeOrder( new TradeOrder( t,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price ) );
        assertTrue( !t.mailbox().isEmpty() );
    }


    @Test
    public void traderQuit()
    {
        Brokerage b = new Brokerage( new StockExchange() );
        Trader t = new Trader( b, "tester", "check" );
        t.quit();
        assertTrue( !b.getLoggedTraders().contains( t ) );

    }


    @Test
    public void traderGetName()
    {
        Trader t = new Trader( new Brokerage( new StockExchange() ),
            "tester",
            "check" );
        assertEquals( t.getName(), "tester" );
    }


    @Test
    public void traderGetPsw()
    {
        Trader t = new Trader( new Brokerage( new StockExchange() ),
            "tester",
            "check" );
        assertEquals( t.getPassword(), "check" );
    }


    @Test
    public void traderMsg()
    {
        Trader t = new Trader( new Brokerage( new StockExchange() ),
            "tester",
            "check" );
        t.receiveMessage( "check" );
        assertTrue( t.hasMessages() );
    }


    @Test
    public void traderToString()
    {
        Trader t = new Trader( new Brokerage( new StockExchange() ),
            "tester",
            "check" );
        assertNotNull( t.toString() );
    }


    // --Test Brokerage

    @Test
    public void brokerageAddUser()
    {
        Brokerage t = new Brokerage( new StockExchange() );
        assertEquals( t.addUser( "tester", "check" ), 0 );
        assertTrue( t.getTraders().containsKey( "tester" ) );
    }


    @Test
    public void brokerageLogin()
    {
        Brokerage t = new Brokerage( new StockExchange() );
        assertEquals( t.addUser( "tester", "check" ), 0 );
        assertEquals( t.login( "tester", "check" ), 0 );
        assertTrue( t.getLoggedTraders().contains( t.getTraders()
            .get( "tester" ) ) );
    }


    @Test
    public void borkerageToString()
    {
        Brokerage t = new Brokerage( new StockExchange() );
        assertNotNull( t.toString() );
    }


    // --Test StockExchange
    @Test
    public void StockExGetQuote()
    {
        StockExchange ex = new StockExchange();
        ex.listStock( symbol, "tester", price );
        assertEquals( ex.getQuote( symbol ), "tester" + " (" + symbol
            + ")\nPrice: " + price + "  hi: " + price + "  lo: " + price
            + "  vol: 0\nAsk: none  Bid: none" );
    }


    @Test
    public void StockExListStock()
    {
        StockExchange ex = new StockExchange();
        ex.listStock( symbol, "tester", price );
        assertEquals( false, ex.getListedStocks().isEmpty() );
    }


    @Test
    public void StockExPlaceOrder()
    {
        StockExchange ex = new StockExchange();
        ex.listStock( symbol, "tester", price );
        Brokerage b = new Brokerage( ex );
        Trader tester = new Trader( b, "tester", "test" );
        TradeOrder check = new TradeOrder( tester,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        ex.placeOrder( check );

        assertFalse( ex.getListedStocks()
            .get( symbol )
            .getBuyOrders()
            .isEmpty() );
        assertTrue( tester.hasMessages() );
    }


    @Test
    public void StockExToString()
    {
        StockExchange ex = new StockExchange();
        assertNotNull( ex.toString() );
    }


    // --Test Stock
    @Test
    public void getStockQuote()
    {
        Stock stk = new Stock( symbol, "test", price );
        assertEquals( stk.getQuote(), "test (" + symbol + ")\nPrice: " + price
            + "  hi: " + price + "  lo: " + price
            + "  vol: 0\nAsk: none  Bid: none" );
    }


    @Test
    public void placeStockOrder()
    {
        StockExchange ex = new StockExchange();
        Stock stk = new Stock( symbol, "test", price );
        ex.listStock( symbol, "test", price );
        Brokerage b = new Brokerage( ex );
        Trader tester = new Trader( b, "tester", "check" );
        TradeOrder tradeO = new TradeOrder( tester,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );

        stk.placeOrder( tradeO );
        assertEquals( stk.getBuyOrders().isEmpty(), false );
        assertTrue( tester.hasMessages() );

    }


    @Test
    public void executeStockOrders()
    {
        StockExchange ex = new StockExchange();
        ex.listStock( symbol, "test", price );
        Brokerage b = new Brokerage( ex );
        Trader alpha = new Trader( b, "tester", "check" );
        Trader beta = new Trader( b, "tester", "check" );
        ex.getListedStocks()
            .get( symbol )
            .getBuyOrders()
            .add( new TradeOrder( alpha,
                symbol,
                buyOrder,
                marketOrder,
                numShares,
                price ) );
        ex.getListedStocks()
            .get( symbol )
            .getSellOrders()
            .add( new TradeOrder( beta,
                symbol,
                buyOrder,
                marketOrder,
                numShares,
                price ) );
        ex.getListedStocks().get( symbol ).executeOrders();
        assertTrue( alpha.hasMessages() && beta.hasMessages() );
    }


    @Test
    public void stockToString()
    {
        Stock test = new Stock( symbol, "test", price );
        assertNotNull( test.toString() );
    }

    // Remove block comment below to run JUnit test in console
    /*
     * public static junit.framework.Test suite() { return new
     * JUnit4TestAdapter( JUSafeTradeTest.class ); }
     * 
     * public static void main( String args[] ) {
     * org.junit.runner.JUnitCore.main( "JUSafeTradeTest" ); }
     */

    /*
     * public static junit.framework.Test suite() { return new
     * JUnit4TestAdapter( JUSafeTradeTest.class ); }
     * 
     * public static void main( String args[] ) {
     * org.junit.runner.JUnitCore.main( "JUSafeTradeTest" ); }
     */
}
