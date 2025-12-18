package es.upm.etsisi.poo.exceptions;

public class TicketAlreadyClosedException extends RuntimeException {
    public TicketAlreadyClosedException(String message) {
        super(message);
    }
}
