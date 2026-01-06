package es.upm.etsisi.poo.exceptions.ticket;

public class ServiceAlreadyInTicketException extends RuntimeException {
    public ServiceAlreadyInTicketException() {
        super("Ese servicio ya se encuentra en el ticket");
    }
}
