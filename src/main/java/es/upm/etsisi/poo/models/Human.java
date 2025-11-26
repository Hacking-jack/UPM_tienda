package es.upm.etsisi.poo.models;

import java.util.ArrayList;

public abstract class Human {

    protected String id;
    protected String nombre;
    protected String email;
    protected ArrayList<String> tickets;

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public void addTicket(String ticketId) {
        tickets.add(ticketId);
    }

    public ArrayList<String> getTickets() {
        return tickets;
    }

    public boolean contains(Ticket ticket){
        return tickets.contains(ticket);
    }

    public abstract String getId();
}
