package es.upm.etsisi.poo.exceptions.ticket;

public class ProductNotInTicketException extends RuntimeException {
    public ProductNotInTicketException(String id) {

        super("El producto con id: "+id+" no se encuentra en el ticket indicado");
    }
}
