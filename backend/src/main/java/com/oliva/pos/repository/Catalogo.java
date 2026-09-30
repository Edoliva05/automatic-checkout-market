package com.oliva.pos.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oliva.pos.model.Prodotto;

@Repository
public class Catalogo {
    
    Map<String, Prodotto> productDB;

    public Catalogo(){
        this.productDB = new HashMap<>();
        populateDB();
    }

    //this method populates the DB (hashmap because for now it's a mock) with a set of tests products
    //              key --> barCode    value --> Product Object
    private void populateDB(){
        productDB.put("800123", new Prodotto("Pasta Barilla 500g", "800123", 1.20));
        productDB.put("800456", new Prodotto("Passata di Pomodoro", "800456", 1.50));
        productDB.put("800789", new Prodotto("Acqua Naturale 1.5L", "800789", 0.40));
        productDB.put("800000", new Prodotto("Sacca Shopper", "800000", 0.10));
        productDB.put("9771122330009", new Prodotto("Iphone 14", "9771122330009", 599));
    }

    public Prodotto findProductByCode(String bar_code){
        return productDB.get(bar_code);
    }
}
