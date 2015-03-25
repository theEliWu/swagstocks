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
 * @author Eric Zheng
 * @author Eli Wu
 * @version 3/24/15
 * @author Assignment: JM Chapter 19 - SafeTrade
 * 
 * @author Sources: me
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
    public void tradeOrderConstructor()
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
    public void TradeOrderToString()
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
    public void tradeOrderGetTrader()
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
    public void tradeOrderGetSymbol()
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
    public void tradeOrderIsBuy()
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
    public void tradeOrderIsSell()
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
    public void tradeOrderIsMarket()
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
    public void tradeOrderIsLimit()
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
    public void tradeOrderGetShares()
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
    public void tradeOrderGetPrice()
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
    public void tradeOrderSubtractShares()
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
    public void traderWindowConstructor()
    {
        TraderWindow tw = new TraderWindow( null );
        assertNotNull( tw );
    }


    @Test
    public void traderWindowShowMessage()
    {
        TraderWindow tw = new TraderWindow( null );
        assertNotNull( tw );
        tw.showMessage( null );
    }


    // --Test PriceComparator

    @Test
    public void priceComparatorAscending()
    {
        PriceComparator pc = new PriceComparator();
        TradeOrder to1 = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        TradeOrder to2 = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price + 5.0 );
        assertNotNull( pc );
        assertNotNull( to1 );
        assertNotNull( to2 );
        assertEquals( "<< PriceComparator: compare(" + to1 + ", " + to2
            + ") should be " + (int)( to1.getPrice() - to2.getPrice() + 0.5 )
            + ">>",
            (int)( to1.getPrice() - to2.getPrice() + 0.5 ),
            pc.compare( to1, to2 ) );
    }


    @Test
    public void priceComparatorDescending()
    {
        PriceComparator pc = new PriceComparator( false );
        TradeOrder to1 = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price + 5.0 );
        TradeOrder to2 = new TradeOrder( null,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );
        assertNotNull( pc );
        assertNotNull( to1 );
        assertNotNull( to2 );
        assertEquals( "<< PriceComparator: compare(" + to1 + ", " + to2
            + ") should be " + (int)( to2.getPrice() - to1.getPrice() + 0.5 )
            + ">>",
            (int)( to2.getPrice() - to1.getPrice() + 0.5 ),
            pc.compare( to1, to2 ) );
    }


    // --Test Trader

    @Test
    public void traderToString()
    {
        Trader tr = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        assertNotNull( tr.toString() );
    }


    @Test
    public void traderGetName()
    {
        Trader tr = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        assertEquals( tr.getName(), "Test" );
    }


    @Test
    public void traderGetPassword()
    {
        Trader tr = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        assertEquals( tr.getPassword(), "Test" );
    }


    @Test
    public void traderMessage()
    {
        Trader tr = new Trader( new Brokerage( new StockExchange() ),
            "Test",
            "Test" );
        tr.receiveMessage( "Test" );
        assertTrue( tr.hasMessages() );
    }


    // --Test Brokerage

    public void brokerageAddUser()
    {
        Brokerage br = new Brokerage( new StockExchange() );
        assertEquals( br.addUser( "Test", "Test" ), 0 );
        assertTrue( br.getTraders().containsKey( "Test" ) );
    }


    public void brokerageLogin()
    {
        Brokerage br = new Brokerage( new StockExchange() );
        assertEquals( br.addUser( "Test", "Test" ), 0 );
        assertEquals( br.login( "Test", "Test" ), 0 );
        assertTrue( br.getLoggedTraders().contains( br.getTraders()
            .get( "Test" ) ) );
    }


    // --Test StockExchange

    // TODO your tests here

    // --Test Stock
    @Test
    public void getStockQuote()
    {
        Stock stock = new Stock( symbol, "Test", price );
        assertEquals( stock.getQuote(), "Test (" + symbol + ")\nPrice: "
            + price + "  hi: " + price + "  lo: " + price
            + "  vol: 0\nAsk: none Bid: none" );
    }


    @Test
    public void placeStockOrder()
    {
        StockExchange exchange = new StockExchange();
        Stock gggl = new Stock( symbol, "Test", price );
        exchange.listStock( symbol, "Test", price );
        Brokerage broke = new Brokerage( exchange );
        Trader testr = new Trader( broke, "Test", "Test" );
        TradeOrder test = new TradeOrder( testr,
            symbol,
            buyOrder,
            marketOrder,
            numShares,
            price );

        gggl.placeOrder( test );
        assertEquals( gggl.getBuyOrders().isEmpty(), false );
        assertTrue( testr.hasMessages() );

    }

    // Remove block comment below to run JUnit test in console
    /*
     * public static junit.framework.Test suite() { return new
     * JUnit4TestAdapter( JUSafeTradeTest.class ); }
     * 
     * public static void main( String args[] ) {
     * org.junit.runner.JUnitCore.main( "JUSafeTradeTest" ); }
     */
}
