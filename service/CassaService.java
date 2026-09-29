package service;

import model.Prodotto;
import model.RigaScontrino;
import model.Scontrino;
import repository.Catalogo;

//class Controller, stands between class Scontrino and Catalogo
public class CassaService {

    Scontrino currentChart;
    Catalogo catalog;

    public CassaService(Catalogo catalog){ //Dependency injection of the DB
        this.catalog = catalog;
    }

    //starts the new chart 
    public void startNewChart(){
        this.currentChart = new Scontrino();
    }

    //method that given the barcode interrogate the DB if exists, create a new row and pass it to the chart
    //if the product is currencly in the chart, increase the quantity of it
    //if not exists: return an exception
    public void scan(String barcode){
        Prodotto currentProduct = catalog.findProductByCode(barcode);

        if(currentProduct != null){

            double price = currentProduct.getPrice();

            if(currentChart.isAlreadyInChart(currentProduct)){
                currentChart.increaseRowQuantity(currentProduct);
            }else{
                RigaScontrino receiptRow = new RigaScontrino(currentProduct, 1, price);
                currentChart.addRow(receiptRow);
            }

        }
    }

    //method that calls the function to compute the charts's total and close the current chart
    public double payAndCloseChart(){
        double total = currentChart.computeTotal();
        currentChart = null;
        return total;
    }


}
