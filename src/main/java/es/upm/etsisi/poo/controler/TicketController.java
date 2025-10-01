package es.upm.etsisi.poo.controler;
/*
Esto controla los tickets
ticket new (resetea ticket en curso)
ticket add <prodId> <cantidad> (agrega al ticket la cantidad de ese producto)
ticket remove <prodId> (elimina todas las apariciones del producto, revisa si existe el id )
ticket print (imprime factura)
 */

import es.upm.etsisi.poo.models.Product;
import es.upm.etsisi.poo.models.Ticket;

public class TicketController {

    private Ticket ticket;

    public TicketController(Ticket ticket){
        this.ticket = ticket;

    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public static void newTicket(){
        //Se hará en el switch
    }
    public void addAproduct(Product product, int quantity){
        for(int i = 0; i<quantity; i++)
            ticket.getProducts().add(product);
    }
    public void remove(Product product){
        ticket.getProducts().remove(product);
    }
    public void print(){//Hay que terminarlo
        for (int i = 0; i < ticket.getProducts().size(); i++) {
            System.out.println(ticket.getProducts().get(i).toString());
        }
    }
}
