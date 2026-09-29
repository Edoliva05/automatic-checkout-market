import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Scontrino {
    private List<RigaScontrino> rows = new ArrayList<>();
    private LocalDateTime date;

    public Scontrino(){
        
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
}