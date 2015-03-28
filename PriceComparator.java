/**
 * A price comparator for trade orders.
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{
    private boolean asc;
    
    public PriceComparator() {
        asc = true;
    }
    
    public PriceComparator( Boolean ascending ) {
        asc = ascending;
    }
    
    @Override
    public int compare( TradeOrder t1, TradeOrder t2 ) {
        if ( t1.isMarket() && t2.isMarket() )
            return 0;
        else if ( t1.isMarket() && t2.isLimit() )
            return -1;
        else if ( t1.isLimit() && t2.isMarket() )
            return 1;
        else {
            if ( asc == true )
                return (int)( t1.getPrice() - t2.getPrice() );
            else
                return (int)( t2.getPrice() - t1.getPrice() );
        }
    }
}
