package bundle;

/**
 * This is a class that represents a bundle of stock shares.
 * It is used to assist in our NetBenefit class.
 * The amount of shares is mutable as we don't have to sell all the stocks, but the price is not.
 *
 * @authors: P3200262, P3200298
 * @info: Made for the course of Data Structures @ AUEB 2021-2022
 **/


public class Bundle {
    private final int price;
    private int shares;
    
    
    public Bundle(int shares, int price) {
        this.shares = shares;
        this.price = price;
    }
    
    public void setShares(int shares) {
        this.shares = shares;
    }
    
    public int getShares() {
        return this.shares;
    }
    
    public int getPrice() {
        return this.price;
    }
}
