/**
 * A price comparator for trade orders.
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{
    private boolean asc;
    
    public PriceComparator() {
        super();
        asc = true;
    }
    
    public PriceComparator( Boolean ascending ) {
        super();
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
            int t1PriceCents = (int) t1.getPrice() * 100;
            int t2PriceCents = (int) t2.getPrice() * 100;
            int compared = t1PriceCents - t2PriceCents;
            if ( !asc )
                compared *= -1;
            return compared;
        }
    }
}
