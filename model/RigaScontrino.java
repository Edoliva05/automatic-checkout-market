package model;
public class RigaScontrino {
    private Prodotto product;
    private int quantity;
    private double productPrice;

    public Prodotto getProduct(){
        return this.product;
    }

    public double getProductPrice(){
        return this.productPrice;
    }

    public int getQuantity(){
        return this.quantity;
    }
}