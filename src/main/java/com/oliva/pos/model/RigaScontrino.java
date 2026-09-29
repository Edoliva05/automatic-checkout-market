package com.oliva.pos.model;
public class RigaScontrino {
    private Prodotto product;
    private int quantity;
    private double productPrice;

    public RigaScontrino(Prodotto product, int quantity, double productPrice){
        this.product = product;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public Prodotto getProduct(){
        return this.product;
    }

    public double getProductPrice(){
        return this.productPrice;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void increaseQuantity(){
        this.quantity += 1;
    }
}