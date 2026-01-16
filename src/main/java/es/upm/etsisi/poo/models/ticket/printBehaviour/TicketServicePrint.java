package es.upm.etsisi.poo.models.ticket.printBehaviour;

import es.upm.etsisi.poo.exceptions.ticket.*;
import es.upm.etsisi.poo.models.product.Product;
import es.upm.etsisi.poo.models.product.ProductService;
import es.upm.etsisi.poo.models.ticket.States;
import es.upm.etsisi.poo.models.ticket.Ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class TicketServicePrint implements TicketPrintBehaviour<ProductService>{
    @Override
    public void printAndClose(Ticket<ProductService> ticket) {
        if (ticket.getEstado() == States.VACIO) {
            throw new NotSatisfiedMinimumRequirementsException();
        }else{
            if((ticket.getEstado() != States.CERRADO) && !ticket.comprobarCaducidad()){
                throw new ExpiredProductsException();
            }
            if (ticket.getEstado() != States.CERRADO) {
                ticket.setEstado(States.CERRADO);
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("-yy-MM-dd-HH:mm");
            ticket.setIdTicket(ticket.getIdTicket()+LocalDateTime.now().format(formatter));
            print(ticket);
        }
    }

    public void print(Ticket<ProductService> ticket){
        System.out.println(getStringPrint(ticket));
    }

    @Override
    public String getStringPrint(Ticket<ProductService> ticket) {
        StringBuilder sb = new StringBuilder();
        ArrayList<ProductService> products=ticket.getProducts();
        products.sort(Comparator.comparing(Product::getId));
        sb.append(String.format("Ticket : %s", ticket.getIdTicket()));
        if(ticket.getEstado()!=States.VACIO)    sb.append("\nServices Included:");

        for (Product p : products) {
            sb.append("\n  ").append(p.toString());
        }

        return sb.toString();
    }
}
