package es.upm.etsisi.poo.models.ticket;

public class TicketProduct extends Ticket{
    //private final TicketType tipo = TicketType.PRODUCTS;
    public TicketProduct(String id){

    }
    public TicketProduct(){

    }
    public TicketType getType() {
    return TicketType.PRODUCTS;
    }
}
