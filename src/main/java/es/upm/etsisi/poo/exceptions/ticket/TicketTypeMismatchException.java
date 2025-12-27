package es.upm.etsisi.poo.exceptions.ticket;

public class TicketTypeMismatchException extends RuntimeException {
    public TicketTypeMismatchException() {
        super("El tipo de ticket no es compatible, las empresas pueden tener tickets de servicios (-s) o combinados (-c) y" +
                "los clientes particulares tickets de productos (-p), la opcion por defecto es ticket de productos");
    }
}
