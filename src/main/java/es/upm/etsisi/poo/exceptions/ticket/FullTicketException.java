package es.upm.etsisi.poo.exceptions.ticket;

public class FullTicketException extends RuntimeException {
    public FullTicketException(String ticketPrint) {
        super("No se pudieron añadir todos los productos, se han añadido hasta llegar al limite de 100\n" +
                ticketPrint);
    }
}
