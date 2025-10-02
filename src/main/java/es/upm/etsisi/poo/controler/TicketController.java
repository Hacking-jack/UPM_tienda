package es.upm.etsisi.poo.controler;
/*
Esto controla los tickets
ticket new (resetea ticket en curso)
ticket add <prodId> <cantidad> (agrega al ticket la cantidad de ese producto)
ticket remove <prodId> (elimina todas las apariciones del producto, revisa si existe el id )
ticket print (imprime factura)
 */

import es.upm.etsisi.poo.models.Categories;
import es.upm.etsisi.poo.models.Product;
import es.upm.etsisi.poo.models.Ticket;

public class TicketController {

    private Ticket ticket;

    public TicketController(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void newTicket() {
        this.ticket = new Ticket();
    }

    public void add(Product product, int quantity) {
        for (int i = 0; i < quantity; i++)
            ticket.getProducts().add(product);
        print();
    }

    public void remove(Product product) {
        ticket.getProducts().remove(product);
    }

    public void print() {//Hay que terminarlo
        double precioTotal = 0, descuentoTotal = 0, precioFinal = 0;
        for (int i = 0; i < ticket.getProducts().size(); i++) {
            precioTotal += ticket.getProducts().get(i).getPrice();
            descuentoTotal += Categories.getDiscount(ticket.getProducts().get(i).getCategories()) * ticket.getProducts().get(i).getPrice();
            precioFinal = precioTotal - descuentoTotal;
            if(Categories.getDiscount(ticket.getProducts().get(i).getCategories()) == 0.0) {
                System.out.println(ticket.getProducts().get(i).toString());
                System.out.println("Total price: "+precioTotal);
                System.out.println("Total discount: "+descuentoTotal);
                System.out.println("Final price: "+precioFinal);
            }
            else{
                System.out.println(ticket.getProducts().get(i).toString()+"**discount -"+Categories.getDiscount(ticket.getProducts().get(i).getCategories())*ticket.getProducts().get(i).getPrice());
                System.out.println("Total price: "+precioTotal);
                System.out.println("Total discount: "+descuentoTotal);
                System.out.println("Final price: "+precioFinal);
            }
        }
    }
}
