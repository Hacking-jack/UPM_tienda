package es.upm.etsisi.poo.exceptions;

public class TicketClosedException extends RuntimeException {
    public TicketClosedException(String message) {
        super(message);
    }
}
