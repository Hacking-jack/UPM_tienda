package es.upm.etsisi.poo.models.user;

import es.upm.etsisi.poo.models.ticket.Ticket;

import java.util.ArrayList;

public abstract class User {

    protected Integer numId;
    protected String id;
    protected String nombre;
    protected String email;
    protected ArrayList<String> tickets;

    public boolean isNew() {
        return this.numId == null;
    }
    public String getNombre() {
        return nombre;
    }

    public void addTicket(String ticketId) {
        tickets.add(ticketId);
    }

    public ArrayList<String> getTickets() {
        return tickets;
    }

    public boolean contains(Ticket ticket) {
        return tickets.contains(ticket);
    }

    public abstract String getId();
}
