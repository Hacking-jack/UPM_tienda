package es.upm.etsisi.poo.exceptions.ticket;

public class CashierTicketMismatchException extends RuntimeException {
    public CashierTicketMismatchException() {
        super("Ese ticket no pertenece a ese cajero");
    }
}
