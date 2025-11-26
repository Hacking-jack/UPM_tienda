package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.BASES_DE_DATOS.TicketDB;
import es.upm.etsisi.poo.models.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TicketController {


    public static void newTicket(String id) {
        if(id==null){
            TicketDB.addTicket(new Ticket());
        }else{
            TicketDB.addTicket(new Ticket(id));
        }
    }

    public static void addProduct(String ticketId, Product product, int quantity) {
        Ticket ticket = findId(ticketId);
        for (int i = 0; i < quantity; i++) {
            if (!ticket.addProduct(product)) {
                System.out.println("No se pudieron añadir todos los productos, se han añadido " + i +
                        " hasta llegar al limite de 100");
                break;
            }
        }
    }

    public static void addProductPers(String ticketId, Product product, int quantity, String[] pers) {
        if (product instanceof ProductCustom) {
            Ticket ticket = findId(ticketId);
            Product clone = product.clone();
            for (int i = 0; i < quantity; i++) {
                if (!ticket.addProduct(clone)) {
                    System.out.println("No se pudieron añadir todos los productos, se han añadido " + i +
                            " hasta llegar al limite de 100.");
                    break;
                }
            }
            ((ProductCustom) product).addPers(pers);
        } else {
            System.out.println("No se puede personalizar un objeto no personalizable");
        }
    }

    static public Ticket findId(String id) {
        return TicketDB.findId(id);
    }

    public static void remove(Ticket ticket, Product product) {
        ticket.removeProduct(product);
    }

    public static void list() {
        ArrayList<Ticket> tickets = TicketDB.listProducts();
        for (Ticket t : tickets) {
            System.out.println(t.toString());
        }
    }

    public static void print(String ticketId) {
        Ticket t=TicketDB.findId(ticketId);
        if(t!=null) {
            t.printAndClose();
        }else{
            System.out.println("Ticket no encontrado");
        }
    }
}