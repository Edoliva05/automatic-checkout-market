import repository.Catalogo;
import service.CassaService;

public class Main {
    
    public static void main(String[] args) {

        //instantiate catalogo and controller
        Catalogo catalogo = new Catalogo();
        CassaService cassaService = new CassaService(catalogo);

        //starting a new cart
        cassaService.startNewCart();

        //simulating the product's scanning
        cassaService.scan("800456"); //product 1
        cassaService.scan("800456"); //seocnd item of product 1
        cassaService.scan("800789"); //product 2

        try {
            cassaService.scan("800433"); //product tath does not exists
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        

        //print the total
        System.out.println("Cart's total: " + cassaService.payAndCloseCart());
        
    }

}
