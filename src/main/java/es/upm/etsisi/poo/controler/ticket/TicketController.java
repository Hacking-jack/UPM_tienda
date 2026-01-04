package es.upm.etsisi.poo.controler.ticket;

import es.upm.etsisi.poo.dataBase.TicketDB;
import es.upm.etsisi.poo.exceptions.product.NotCustomizableProductException;
import es.upm.etsisi.poo.exceptions.ticket.*;
import es.upm.etsisi.poo.models.product.Product;
import es.upm.etsisi.poo.models.product.ProductBasic;
import es.upm.etsisi.poo.models.product.ProductBasicCustom;
import es.upm.etsisi.poo.models.product.ProductMeetingFood;
import es.upm.etsisi.poo.models.ticket.*;

import java.util.Objects;

public class TicketController {


    public static String newTicket(String id, String tipo) {
        assert (Objects.equals(tipo, "BASIC")) || (Objects.equals(tipo, "SERVICE")) || (Objects.equals(tipo, "MIX"));
        if (id == null) {
            Ticket ticket;
            ticket= switch (tipo){
              case "BASIC"->  new TicketProduct();
              case "SERVICE"-> new TicketService();
              case "MIX" -> new TicketMix();
              default -> throw new IllegalStateException("Unexpected value: " + tipo);
            };
            id = ticket.getIdTicket();
            TicketDB.addTicket(ticket);
            TicketDB.findId(id).print();
        } else {
            if (!TicketDB.existeId(id)) {
                Ticket ticket;
                ticket= switch (tipo){
                    case "BASIC"->  new TicketProduct(id);
                    case "SERVICE"-> new TicketService(id);
                    case "MIX" -> new TicketMix(id);
                    default -> throw new IllegalStateException("Unexpected value: " + tipo);
                };
                TicketDB.addTicket(ticket);
                TicketDB.findId(id).print();
            } else {
                throw new DuplicateTicketIdException();
            }
        }
        return id;
    }

    public static String newTicketBasic(String id) {
        return newTicket(id, "BASIC");
    }

    public static String newTicketService(String id) {
        return newTicket(id, "SERVICE");
    }

    public static String newTicketMix(String id) {
        return newTicket(id, "MIX");
    }


    public static void addProduct(String ticketId, Product product, int quantity) {
        Ticket ticket = findId(ticketId);

        if (ticket.getEstado() == States.CERRADO) {
            throw new TicketClosedException();
        }

        if (ticket.getEstado() == States.VACIO) {
            ticket.setEstado(States.ACTIVO);
        }

        for (int i = 0; i < quantity; i++) {
            Product clone = product.clone();
            if (!ticket.addProduct(clone)) {
                throw new FullTicketException(ticket.getStringPrint());
            }
        }
        ticket.print();
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

    public static void remove(Ticket ticket, Product product) {
        ticket.removeProduct(product);
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