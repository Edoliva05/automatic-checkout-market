package model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Scontrino {
    private List<RigaScontrino> rows;
    private LocalDateTime date;

    public Scontrino(){
        rows = new ArrayList<>();
        this.date = LocalDateTime.now();
    }
    
    public void addRow(RigaScontrino row){
        this.rows.add(row);
    }

    //function that compute the total of the receipt looping throgh the list of lines
    //and multiplying that for the quantity of the product
    public double computeTotal(){

        double total = 0;

        for(RigaScontrino row : rows){
            total += (row.getProductPrice() * row.getQuantity());
        }

        return total;
    }

    //method called by CassaService to verify if a product is already in the chart
    //returns a boolen
    public boolean isAlreadyInChart(Prodotto product){
        for(RigaScontrino row : rows){
            if(row.getProduct() == product){
                return true;
            }
        }
        return false;
    }

    //takes a row and call the increaseQuantity method
    public void increaseRowQuantity(Prodotto product){
        for(RigaScontrino row : rows){
            if(row.getProduct() == product){
                row.increaseQuantity();
            }
        }
    }
}