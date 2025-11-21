package es.upm.etsisi.poo.BASES_DE_DATOS;

import es.upm.etsisi.poo.models.Ticket;

import java.util.ArrayList;

public class TicketDB {
    //Equivalente a una tabla Tickets en SQL
    static private ArrayList<Ticket> tickets = new ArrayList<>();

    //Equivalente a un insert
    static public void addTicket(Ticket t) {
        tickets.add(t);
    }
    //TODO hacer el resto de consultas como si fuera una base de datos SQL, mirar ProductDB
}
