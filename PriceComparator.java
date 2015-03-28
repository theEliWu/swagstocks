/**
 * A price comparator for trade orders.
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{
    private boolean asc;


    public PriceComparator()
    {
        asc = true;
    }


    public PriceComparator( boolean ascending )
    {
        asc = ascending;
    }


    public int compare( TradeOrder t1, TradeOrder t2 )
    {
        if ( t1.isMarket() && t2.isMarket() )
        {
            return 0;
        }
        if ( t1.isMarket() && t2.isLimit() )
        {
            return -1;
        }
        if ( t2.isMarket() && t1.isLimit() )
        {
            return 1;
        }

        if ( asc )
            return (int)( t1.getPrice() - t2.getPrice() * 100 );
        else
            return (int)( -t1.getPrice() + t2.getPrice() * 100 );
    }
}
