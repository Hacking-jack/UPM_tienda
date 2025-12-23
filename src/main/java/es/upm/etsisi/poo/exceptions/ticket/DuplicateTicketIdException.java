package es.upm.etsisi.poo.exceptions.ticket;

public class DuplicateTicketIdException extends RuntimeException {
    public DuplicateTicketIdException() {
        super("Ya existe un ticket con ese id");
    }
}
