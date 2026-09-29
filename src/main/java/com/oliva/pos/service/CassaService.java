package com.oliva.pos.service;

import org.springframework.stereotype.Service;

import com.oliva.pos.model.Prodotto;
import com.oliva.pos.model.RigaScontrino;
import com.oliva.pos.model.Scontrino;
import com.oliva.pos.repository.Catalogo;


//class Service, stands between class Scontrino and Catalogo
@Service
public class CassaService {

    Scontrino currentCart;
    Catalogo catalog;

    public CassaService(Catalogo catalog){ //Dependency injection of the DB
        this.catalog = catalog;
    }

    //starts the new cart 
    public void startNewCart(){
        this.currentCart = new Scontrino();
    }

    //method that given the barcode interrogate the DB if exists, create a new row and pass it to the cart
    //if the product is currencly in the cart, increase the quantity of it
    //if not exists: return an exception
    public void scan(String barcode){
        Prodotto currentProduct = catalog.findProductByCode(barcode);

        if(currentProduct != null){

            double price = currentProduct.getPrice();

            if(currentCart.isAlreadyInCart(currentProduct)){
                currentCart.increaseRowQuantity(currentProduct);
            }else{
                RigaScontrino receiptRow = new RigaScontrino(currentProduct, 1, price);
                currentCart.addRow(receiptRow);
            }

        }else{
            throw new IllegalArgumentException("Barcode not found: " + barcode);
        }
    }

    //method that calls the function to compute the carts's total and close the current cart
    public double payAndCloseCart(){
        double total = currentCart.computeTotal();
        currentCart = null;
        return total;
    }


}
