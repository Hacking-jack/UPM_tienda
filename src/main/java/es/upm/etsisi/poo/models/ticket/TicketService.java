package es.upm.etsisi.poo.models.ticket;

import es.upm.etsisi.poo.exceptions.ticket.ExpiredProductsException;
import es.upm.etsisi.poo.exceptions.ticket.TicketClosedException;
import es.upm.etsisi.poo.exceptions.ticket.TicketTypeMismatchException;
import es.upm.etsisi.poo.models.product.Product;
import es.upm.etsisi.poo.models.product.ProductMeetingFood;
import es.upm.etsisi.poo.models.product.ProductService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;


public class TicketService extends Ticket{

    public TicketService(String id){
        super(id);
    }
    public TicketService(){
        super();
    }

    public boolean addProduct(Product product){
        throw new TicketTypeMismatchException();
    }

    public void addMeeting(ProductMeetingFood product){
        throw new TicketTypeMismatchException();
    }

    public void addService(ProductService productService){
        if(estado==States.CERRADO){
            throw new TicketClosedException();
        }
        if (products.isEmpty()) {
            estado = States.ACTIVO;
        }
        if(!productService.isExpired() && (products.size() < 100)){
            products.add(productService);
        }
    }

    public void printAndClose() {
        if (estado == States.VACIO) {
            System.out.println("El ticket debe tener al menos un servicio para cerrarse");//TODO sustituir por excepcion
        }else{
            if((estado != States.CERRADO) && !comprobarCaducidad()){
                throw new ExpiredProductsException();
            }
            if (estado != States.CERRADO) {
                estado = States.CERRADO;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("-yy-MM-dd-HH:mm");
            idTicket += LocalDateTime.now().format(formatter);
            print();
        }

    }

    public void print() {
        System.out.println(getStringPrint());
    }

    public String getStringPrint(){
        StringBuilder sb = new StringBuilder();
        products.sort(Comparator.comparing(Product::getName));

        sb.append(String.format("Ticket : %s%n", idTicket));

        for (Product p : products) {
                sb.append("  ").append(p.toString()).append("\n");
        }

        return sb.toString();
    }
}
