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

public class ticketController {
    private Ticket ticket = new Ticket();
    public  ticketController(Ticket ticket){
        this.ticket = ticket;

    }
    public static void newTicket(){
        //Se hará en el switch
    }

}
