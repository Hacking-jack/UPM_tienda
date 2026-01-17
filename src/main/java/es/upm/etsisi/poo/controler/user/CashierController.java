package es.upm.etsisi.poo.controler.user;

import es.upm.etsisi.poo.dataBase.cache.UserRepository;
import es.upm.etsisi.poo.dataBase.cache.TicketRepository;
import es.upm.etsisi.poo.exceptions.user.*;
import es.upm.etsisi.poo.models.user.Cashier;
import es.upm.etsisi.poo.models.user.User;

import java.util.Random;

public class CashierController {


    public static void add(String id, String nombre, String email) {
        if (id.matches("^UW\\d{7}$")) {
            if (UserRepository.existeId(id)) {
                throw new DuplicateIdentifierException(id);
            }
            Cashier nuevo = new Cashier(id, nombre, email);
            UserRepository.addUser(nuevo);
            System.out.println(nuevo);
        } else {
            throw new InvalidDocumentNumberException(id);
        }
    }

    public static void remove(String id) {
        Cashier c = searchId(id);
        UserRepository.removeUser(c);
    }

    public static void list() {
        System.out.println("Cash:");
        for (User user : UserRepository.list()) {
            if (user instanceof Cashier) {
                System.out.println("  " + user);
            }
        }
    }


    public static Cashier searchId(String id) {
        return (Cashier) UserRepository.findId(id);
    }

    public static void cashTickets(String cashId) {
        if (UserRepository.existeId(cashId) && (UserRepository.findId(cashId) instanceof Cashier)) {
            System.out.print("Tickets:\n");

            Cashier aux = (Cashier) UserRepository.findId(cashId);
            for (String t : aux.getTickets()) {
                TicketRepository.title(t);
            }
        } else {
            throw new UserNotFoundException("No existe el cajero");
        }
    }

    public static String generarId() {
        return "UW" + (1_000_000 + new Random().nextInt(9_000_000));
    }

    public static boolean existeId(String id) {
        return UserRepository.existeId(id);
    }
}

