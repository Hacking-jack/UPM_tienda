package es.upm.etsisi.poo.controler.user;

import es.upm.etsisi.poo.dataBase.UserDB;
import es.upm.etsisi.poo.dataBase.TicketDB;
import es.upm.etsisi.poo.exceptions.user.DuplicateIdentifierException;
import es.upm.etsisi.poo.exceptions.user.InvalidDocumentNumberException;
import es.upm.etsisi.poo.exceptions.user.UserNotFoundException;
import es.upm.etsisi.poo.models.user.Cashier;
import es.upm.etsisi.poo.models.user.User;

import java.util.Random;

public class CashierController {


    public static void add(String id, String nombre, String email) {
        if (id.matches("^UW\\d{7}$")) {
            if (UserDB.existeId(id)) {
                throw new DuplicateIdentifierException(id);
            }
            Cashier nuevo = new Cashier(id, nombre, email);
            UserDB.addUser(nuevo);
            System.out.println(nuevo);
        } else {
            throw new InvalidDocumentNumberException(id);
        }
    }

    public static void remove(String id) {
        Cashier c = searchId(id);
        UserDB.removeUser(c);
    }

    public static void list() {
        System.out.println("Cash:");
        for (User user : UserDB.list()) {
            if (user instanceof Cashier) {
                System.out.println("  " + user);
            }
        }
    }


    public static Cashier searchId(String id) {
        return (Cashier) UserDB.findId(id);
    }

    public static void cashTickets(String cashId) {
        if (UserDB.existeId(cashId) && (UserDB.findId(cashId) instanceof Cashier)) {
            System.out.print("Tickets: \n");

            Cashier aux = (Cashier) UserDB.findId(cashId);
            for (String t : aux.getTickets()) {
                TicketDB.title(t);
            }
        } else {
            throw new UserNotFoundException("No existe el cajero");
        }
    }

    public static String generarId() {
        return "UW" + (1_000_000 + new Random().nextInt(9_000_000));
    }

    public static boolean existeId(String id) {
        return UserDB.existeId(id);
    }
}

