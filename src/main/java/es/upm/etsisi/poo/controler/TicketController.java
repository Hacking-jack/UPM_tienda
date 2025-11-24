package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.BASES_DE_DATOS.ProductDB;
import es.upm.etsisi.poo.BASES_DE_DATOS.TicketDB;
import es.upm.etsisi.poo.models.*;

import java.util.ArrayList;

 // TODO tengo que pensar como interactuar con la base de datos
public class TicketController {

    private Ticket ticket;
    private Client client;
    private Cashier cashier;

    public TicketController(Client client, Cashier cashier) {
        this.ticket = new Ticket();
        TicketDB.addTicket(ticket);
        this.cashier = cashier;
        this.client = client;
        cashier.addTicket(ticket.getIdTicket());
        client.addTicket(ticket.getIdTicket());
    }

    public void addProduct(Product product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            if (!ticket.addPoduct(product)) {
                System.out.println("No se pudieron añadir todos los productos, se han añadido " + i +
                        " hasta llegar al limite de 100");
                break;
            }
        }
    }
    public void addProductPers(Product product, int quantity, String[] pers){
        if(product instanceof ProductCustom){
            Product clone=product.clone();
            for (int i = 0; i < quantity; i++) {
                if (!ticket.addPoduct(clone)) {
                    System.out.println("No se pudieron añadir todos los productos, se han añadido " + i +
                            " hasta llegar al limite de 100");
                    break;
                }
            }
            ((ProductCustom) product).addPers(pers);
        }else{
            System.out.println("No se puede personalizar u nobjeto no personalizable");
        }

    }
    public void remove(Product product) {
       ticket.removeProduct(product);
    }
    public void list(){
        ArrayList<Ticket> tickets = TicketDB.
    }



}