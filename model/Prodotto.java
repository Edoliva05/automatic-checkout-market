package model;
public class Prodotto {
    private String barCode;
    private String productName;
    private double price;

    public Prodotto(String productName, String barCode, double price){
        this.productName = productName;
        this.barCode = barCode;
        this.price = price;
    }

    public String getBarCode(){
        return this.barCode;
    }

    public String getProductName(){
        return this.productName;
    }

    public double getPrice(){
        return this.price;
    }

}
