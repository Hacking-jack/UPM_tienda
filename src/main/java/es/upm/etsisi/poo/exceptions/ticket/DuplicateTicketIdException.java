package es.upm.etsisi.poo.exceptions.ticket;

public class DuplicateTicketIdException extends RuntimeException {
    public DuplicateTicketIdException(String message) {
        super(message);
    }
}
