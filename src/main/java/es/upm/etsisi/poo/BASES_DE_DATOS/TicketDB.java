package es.upm.etsisi.poo.BASES_DE_DATOS;

import es.upm.etsisi.poo.Exceptions.TicketNotFound;
import es.upm.etsisi.poo.models.Human;
import es.upm.etsisi.poo.models.Product;
import es.upm.etsisi.poo.models.Ticket;

import java.util.ArrayList;

public class TicketDB {
    //Equivalente a una tabla Tickets en SQL
    static private ArrayList<Ticket> tickets = new ArrayList<>();

    //Equivalente a un insert
    static public void addTicket(Ticket t) {
        tickets.add(t);
    }

    static public Ticket findId(String id) {
        for (Ticket t : tickets) {
            if (t.getIdTicket().equals(id))
                return (t);
        }
        throw new TicketNotFound("No se encontró el ticket con id "+id);
    }

    static public boolean existeId(String id){
        for (Ticket t : tickets) {
            if (t.getIdTicket().equals(id))
                return true;
        }
        return false;
    }

    static public void removeTicket(Ticket t) {
        tickets.remove(t);
    }

    //Equivalente a Select * from products;
    static public ArrayList<Ticket> listProducts() {
        return tickets;
    }

    static public void title(String ticketId){
        Ticket t=findId(ticketId);
        if(t!=null) {
            t.title();
        }else{
            System.out.println("Ticket no encontrado");
        }
    }
}
