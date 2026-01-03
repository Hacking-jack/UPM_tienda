package es.upm.etsisi.poo.models.ticket;

public class TicketMix extends Ticket{
    //private final TicketType tipo = TicketType.MIX;
    public TicketMix(){

    }
    public TicketMix(String id){

    }

    public TicketType getType() {
        return TicketType.MIX;
    }
}
