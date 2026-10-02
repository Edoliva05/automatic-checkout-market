package com.oliva.pos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oliva.pos.model.Scontrino;
import com.oliva.pos.service.CassaService;

@RestController
@CrossOrigin //with taht annotation we can send requests from client from differrent ports
public class CassaController {
    private final CassaService cassaService;

    public CassaController(CassaService cassaService){ //dependency injection
        this.cassaService = cassaService;
    }

    @PostMapping("/cassa-automatica/newCart") //when a POST request come at that endpoint, execute the method below
    public String startNewChart(){
        cassaService.startNewCart();
        return "Success: New cart opened";
    }

    //when received a POST request extract the barcode with annotation @PathVariable and 
    //scans the product
    @PostMapping("/cassa-automatica/scan/{barcode}")
    public ResponseEntity<?> scanProduct(@PathVariable String barcode){
        try {
            cassaService.scan(barcode);
            Scontrino updatedCart = cassaService.getCart();
            return ResponseEntity.ok(updatedCart);  //responding with the complete object
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //when received POST request calls payAndCloseCart method
    @PostMapping("/cassa-automatica/checkout")
    public String checkoutCart(){

        try {
            double total = cassaService.payAndCloseCart();
            String fomattedTotal = String.format("%.2f", total);  //2 decimal
            return "Success: Cart closed. Total to pay: " + fomattedTotal;
        } catch (IllegalStateException e) {
            return "Error: " + e.getMessage();
        }
    }

}
