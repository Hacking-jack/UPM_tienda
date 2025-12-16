package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.dataBase.TicketDB;
import es.upm.etsisi.poo.models.product.ProductBasic;
import es.upm.etsisi.poo.models.product.ProductBasicCustom;
import es.upm.etsisi.poo.models.product.ProductBasicMeeting;
import es.upm.etsisi.poo.models.ticket.States;
import es.upm.etsisi.poo.models.ticket.Ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketController {


    public static String newTicket(String id) {
        if (id == null) {
            id = generarId();
        }
        if (!TicketDB.existeId(id)) {
            TicketDB.addTicket(new Ticket(id));
            TicketDB.findId(id).print();
        } else {
            System.out.println("Ya existe un ticket con ese id");
        }
        return id;
    }

    public static String generarId() {
        String s = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm-"))
                + String.format("%05d", (int) (Math.random() * 10000));
        if (TicketDB.existeId(s)) {
            return generarId();
        }
        return s;
    }

    public static void addProduct(String ticketId, ProductBasic productBasic, int quantity) {
        Ticket ticket = findId(ticketId);
        if (ticket.getEstado() == States.VACIO)
            ticket.setEstado(States.ACTIVO);
        for (int i = 0; i < quantity; i++) {
            if (!ticket.addProduct(productBasic)) {
                System.out.println("No se pudieron añadir todos los productos, se han añadido " + i +
                        " hasta llegar al limite de 100");
                ticket.print();
                break;
            }
        }
        ticket.print();
    }

    //TODO  addProductPers puede llamar a addProduct para reducir código
    public static void addProductPers(String ticketId, ProductBasic productBasic, int quantity, String[] pers) {
        if (productBasic instanceof ProductBasicCustom) {
            Ticket ticket = findId(ticketId);
            ProductBasicCustom clone = (ProductBasicCustom) productBasic.clone();
            clone.addPers(pers);
            for (int i = 0; i < quantity; i++) {
                if (!ticket.addProduct(clone)) {
                    System.out.println("No se pudieron añadir todos los productos, se han añadido " + i +
                            " hasta llegar al limite de 100.");
                    ticket.print();
                    break;
                }
            }
            ticket.print();
        } else {
            System.out.println("No se puede personalizar un objeto no personalizable");
        }
    }
    //TODO revisar esto que es un poco chapuza
    static public void addMeeting(String ticketId, ProductBasicMeeting product, int quantity) {
        Ticket ticket = findId(ticketId);
        ProductBasicMeeting clone = product.clone();
        if (clone.setAsistentes(quantity))
            ticket.addMeeting(clone);
        ticket.print();
    }

    static public Ticket findId(String id) {
        return TicketDB.findId(id);
    }

    public static void remove(Ticket ticket, ProductBasic productBasic) {
        ticket.removeProduct(productBasic);
    }

    public static void list() {
        System.out.println("Ticket List:");
        for (Ticket t : TicketDB.listProducts()) {
            System.out.println("  " + t.list());
        }
    }

    public static void print(String ticketId) {
        if (TicketDB.existeId(ticketId)) {
            TicketDB.findId(ticketId).printAndClose();
        } else {
            System.out.println("Ticket no encontrado");
        }
    }
}