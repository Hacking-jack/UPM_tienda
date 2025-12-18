package es.upm.etsisi.poo.exceptions.ticket;

public class TicketClosedException extends RuntimeException {
    public TicketClosedException(String message) {
        super(message);
    }
}
