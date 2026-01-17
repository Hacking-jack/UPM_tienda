package es.upm.etsisi.poo.models.ticket.printBehaviour;

import es.upm.etsisi.poo.models.product.Product;
import es.upm.etsisi.poo.models.ticket.Ticket;

public interface TicketPrintBehaviour<T extends Product> {
    Class<T> getTipo();
    void printAndClose(Ticket<T> ticket);
    String getStringPrint(Ticket<T> ticket);
    void print(Ticket<T> ticket);
}
