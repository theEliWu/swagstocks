/**
 * A price comparator for trade orders.
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{
    public int compare( TradeOrder t1, TradeOrder t2 ) {
        return (int)  (t1.getShares()*t1.getPrice() 
                        - t2.getPrice()*t2.getShares() );
    }
    
    @Override
    public boolean equals( Object obj ) {
        obj.
    }
    
}
