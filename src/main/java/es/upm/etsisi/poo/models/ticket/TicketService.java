package es.upm.etsisi.poo.models.ticket;

public class TicketService extends Ticket{
    //private final TicketType tipo = TicketType.SERVICES;
    public TicketService(String id){

    }
    public TicketService(){

    }
    @Override
    public TicketType getType() {
        return TicketType.SERVICES;
    }
}
