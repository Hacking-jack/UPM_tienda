package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.BASES_DE_DATOS.TicketDB;
import es.upm.etsisi.poo.models.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TicketController {


    public static String newTicket(String id) {
        if(id==null){
            id=generarId();
        }
            if(!TicketDB.existeId(id)) {
                TicketDB.addTicket(new Ticket(id));
                TicketDB.findId(id).print();
            }else{
                System.out.println("Ya existe un ticket con ese id");
            }
            return id;
    }

    public static String generarId(){
        String s = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm-"))
                + String.format("%05d", (int) (Math.random() * 10000));
        if(TicketDB.existeId(s)){
            return generarId();
        }
        return s;
    }

    public static void addProduct(String ticketId, Product product, int quantity) {
        Ticket ticket = findId(ticketId);
        if (ticket.getEstado() == States.VACIO)
            ticket.setEstado(States.ACTIVO);
        for (int i = 0; i < quantity; i++) {
            if (!ticket.addProduct(product)) {
                System.out.println("No se pudieron añadir todos los productos, se han añadido " + i +
                        " hasta llegar al limite de 100");
                ticket.print();
                break;
            }
        }
        ticket.print();
    }
    //TODO  addProductPers puede llamar a addProduct para reducri codigo
    public static void addProductPers(String ticketId, Product product, int quantity, String[] pers) {
        if (product instanceof ProductCustom) {
            Ticket ticket = findId(ticketId);
            ProductCustom clone = (ProductCustom) product.clone();
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
    static public void addMeeting(String ticketId, ProductMeeting product, int quantity)
    {
        Ticket ticket = findId(ticketId);
        ProductMeeting clone = product.clone();
        clone.setAsistentes(quantity);
        ticket.addMeeting(clone);
    }

    static public Ticket findId(String id) {
        return TicketDB.findId(id);
    }

    public static void remove(Ticket ticket, Product product) {
        ticket.removeProduct(product);
    }

    public static void list() {
        System.out.println("Ticket List:");
        for (Ticket t : TicketDB.listProducts()) {
            System.out.println("  "+t.list());
        }
    }

    public static void print(String ticketId) {
        if(TicketDB.existeId(ticketId)) {
            TicketDB.findId(ticketId).printAndClose();
        }else{
            System.out.println("Ticket no encontrado");
        }
    }
}