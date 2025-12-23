package es.upm.etsisi.poo.controler.human;

import es.upm.etsisi.poo.dataBase.HumanDB;
import es.upm.etsisi.poo.dataBase.TicketDB;
import es.upm.etsisi.poo.exceptions.human.DuplicateIdentifierException;
import es.upm.etsisi.poo.exceptions.human.InvalidDocumentNumberException;
import es.upm.etsisi.poo.exceptions.human.UserNotFoundException;
import es.upm.etsisi.poo.models.human.Cashier;
import es.upm.etsisi.poo.models.human.Human;

import java.util.Random;

public class CashierController {


    public static void add(String id, String nombre, String email) {
        if (id.matches("^UW\\d{7}$")) {
            if (HumanDB.existeId(id)) {
                throw new DuplicateIdentifierException(id);
            }
            Cashier nuevo = new Cashier(id, nombre, email);
            HumanDB.addUser(nuevo);
            System.out.println(nuevo);
        } else
            throw new InvalidDocumentNumberException(id);
    }

    public static void remove(String id) {
        Cashier c = searchId(id);
        HumanDB.removeHuman(c);
    }

    public static void list() {
        System.out.println("Cash:");
        for (Human human : HumanDB.list()) {
            if (human instanceof Cashier) {
                System.out.println("  " + human.toString());
            }
        }
    }


    public static Cashier searchId(String id) {
        return (Cashier) HumanDB.findId(id);
    }

    public static void cashTickets(String cashId) {
        if (HumanDB.existeId(cashId) && HumanDB.findId(cashId) instanceof Cashier) {
            System.out.print("Tickets: \n");

            Cashier aux = (Cashier) HumanDB.findId(cashId);
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
        return HumanDB.existeId(id);
    }
}

