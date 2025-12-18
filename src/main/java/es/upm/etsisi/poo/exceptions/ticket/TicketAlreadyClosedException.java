package es.upm.etsisi.poo.exceptions.ticket;

public class TicketAlreadyClosedException extends RuntimeException {
    public TicketAlreadyClosedException(String message) {
        super(message);
    }
}
