package es.upm.etsisi.poo.dataBase;

import es.upm.etsisi.poo.exceptions.ticket.TicketNotFoundException;
import es.upm.etsisi.poo.models.ticket.Ticket;

import java.util.ArrayList;

public class TicketDB {
    //Equivalente a una tabla Tickets en SQL
    static private final ArrayList<Ticket> tickets = new ArrayList<>();

    //Equivalente a un insert
    static public void addTicket(Ticket t) {
        tickets.add(t);
    }

    static public Ticket findId(String id) {
        for (Ticket t : tickets) {
            if (t.getIdTicket().equals(id))
                return (t);
        }
        throw new TicketNotFoundException(id);
    }

    static public boolean existeId(String id) {
        for (Ticket t : tickets) {
            if (t.getIdTicket().equals(id))
                return true;
        }
        return false;
    }

    //Equivalente a Select * from products;
    static public ArrayList<Ticket> listProducts() {
        return tickets;
    }

    static public void title(String ticketId) {
        Ticket t = findId(ticketId);
        t.title();
    }
}
