package es.upm.etsisi.poo.controler;
/*
Esto controla los tickets
ticket new (resetea ticket en curso)
ticket add <prodId> <cantidad> (agrega al ticket la cantidad de ese producto)
ticket remove <prodId> (elimina todas las apariciones del producto, revisa si existe el id )
ticket print (imprime factura)
 */

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

}
