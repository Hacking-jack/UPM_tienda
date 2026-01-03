package es.upm.etsisi.poo.dataBase;

import es.upm.etsisi.poo.exceptions.ticket.TicketNotFoundException;
import es.upm.etsisi.poo.models.ticket.Ticket;

import java.util.ArrayList;
//TODO cache de base de datos

public class TicketDB {
    //Equivalente a una tabla Tickets en SQL
    private static final ArrayList<Ticket> tickets = new ArrayList<>();

    //Equivalente a un insert
    public static void addTicket(Ticket t) {
        tickets.add(t);
    }

    public static Ticket findId(String id) {
        for (Ticket t : tickets) {
            if (t.getIdTicket().equals(id)) {
                return (t);
            }
        }
        throw new TicketNotFoundException(id);
    }

    public static boolean existeId(String id) {
        for (Ticket t : tickets) {
            if (t.getIdTicket().equals(id)) {
                return true;
            }
        }
        return false;
    }

    //Equivalente a Select * from products;
    public static ArrayList<Ticket> listProducts() {
        return tickets;
    }

    public static void title(String ticketId) {
        Ticket t = findId(ticketId);
        t.title();
    }
}
