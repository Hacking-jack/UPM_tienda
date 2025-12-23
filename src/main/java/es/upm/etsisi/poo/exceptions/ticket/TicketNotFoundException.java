package es.upm.etsisi.poo.exceptions.ticket;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String id) {
        super("No se encontró el ticket con id: " + id);
    }
}
