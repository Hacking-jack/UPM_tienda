package es.upm.etsisi.poo.exceptions.ticket;

public class TicketClosedException extends RuntimeException {
    public TicketClosedException() {
        super("No se pueden añadir artículos a un ticket cerrado");
    }
}
