package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.BASES_DE_DATOS.TicketDB;
import es.upm.etsisi.poo.models.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// TODO tengo que pensar como interactuar con la base de datos
public class TicketController {


    public static void newTicket(String id) {
        TicketDB.addTicket(new Ticket(id));
    }

    public static void addProduct(Ticket ticket, Product product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            if (!ticket.addProduct(product)) {
                System.out.println("No se pudieron añadir todos los productos, se han añadido " + i +
                        " hasta llegar al limite de 100");
                break;
            }
        }
    }

    public static void addProductPers(Ticket ticket ,Product product, int quantity, String[] pers) {
        if (product instanceof ProductCustom) {
            Product clone = product.clone();
            for (int i = 0; i < quantity; i++) {
                if (!ticket.addProduct(clone)) {
                    System.out.println("No se pudieron añadir todos los productos, se han añadido " + i +
                            " hasta llegar al limite de 100");
                    break;
                }
            }
            ((ProductCustom) product).addPers(pers);
        } else {
            System.out.println("No se puede personalizar un objeto no personalizable");
        }

    }
    static public Ticket findId(String id)
    {
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

    public static String generarId() {
        return String.format(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm-"))
                + String.format("%05d", (int) (Math.random() * 100000)));
    }

    public static void print(String ticketId) {
        System.out.println(TicketDB.findId(ticketId));
    }


}