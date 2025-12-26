package es.upm.etsisi.poo.controler.ticket;

import es.upm.etsisi.poo.dataBase.TicketDB;
import es.upm.etsisi.poo.exceptions.product.NotCustomizableProductException;
import es.upm.etsisi.poo.exceptions.ticket.*;
import es.upm.etsisi.poo.models.product.ProductBasic;
import es.upm.etsisi.poo.models.product.ProductBasicCustom;
import es.upm.etsisi.poo.models.product.ProductMeetingFood;
import es.upm.etsisi.poo.models.ticket.States;
import es.upm.etsisi.poo.models.ticket.Ticket;

public class TicketController {


    public static String newTicket(String id) {
        if (id == null) {
            Ticket ticket = new Ticket();
            id = ticket.getIdTicket();
            TicketDB.addTicket(ticket);
            TicketDB.findId(id).print();
        } else {
            if (!TicketDB.existeId(id)) {
                TicketDB.addTicket(new Ticket(id));
                TicketDB.findId(id).print();
            } else {
                throw new DuplicateTicketIdException();
            }
        }
        return id;
    }


    public static void addProduct(String ticketId, ProductBasic productBasic, int quantity) {
        Ticket ticket = findId(ticketId);
        if (ticket.getEstado() != States.CERRADO) {
            if (ticket.getEstado() == States.VACIO) {
                ticket.setEstado(States.ACTIVO);
            }
            ProductBasic clone = productBasic.clone();
            for (int i = 0; i < quantity; i++) {
                if (!ticket.addProduct(clone)) {
                    throw new FullTicketException(ticket.getStringPrint());
                }
            }
            ticket.print();
        } else {
            throw new TicketClosedException();
        }
    }

    //TODO  addProductPers puede llamar a addProduct para reducir código
    public static void addProductPers(String ticketId, ProductBasic productBasic, int quantity, String[] pers) {
        if (productBasic instanceof ProductBasicCustom) {
            Ticket ticket = findId(ticketId);
            if (ticket.getEstado() != States.CERRADO) {
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
                throw new TicketClosedException();
            }
        } else {
            throw new NotCustomizableProductException();
        }
    }

    //TODO revisar esto que es un poco chapuza
    public static void addMeeting(String ticketId, ProductMeetingFood product, int quantity) {
        Ticket ticket = findId(ticketId);
        if (ticket.getEstado() != States.CERRADO) {
            ProductMeetingFood clone = product.clone();
            if (clone.setAsistentes(quantity)) {
                ticket.addMeeting(clone);
            }
            ticket.print();
        } else {
            throw new TicketClosedException();
        }
    }

    public static Ticket findId(String id) {
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
            Ticket ticket = TicketDB.findId(ticketId);
            ticket.printAndClose();
        } else {
            throw new TicketNotFoundException(ticketId);
        }
    }
}