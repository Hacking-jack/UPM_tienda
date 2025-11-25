package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.BASES_DE_DATOS.HumanDB;
import es.upm.etsisi.poo.BASES_DE_DATOS.TicketDB;
import es.upm.etsisi.poo.models.Cashier;
import es.upm.etsisi.poo.models.Client;
import es.upm.etsisi.poo.models.Human;

import java.util.Random;

public class CashierController {


    public CashierController() {

    }


    public static void add(String id, String nombre, String email) {
        if (id != null && searchId(id) != null) {
            System.out.println("Error. Ya existe un cajero con ese id.");
            return;
        }
        Cashier nuevo = new Cashier(id, nombre, email);
        HumanDB.addUser(nuevo);
        System.out.println(nuevo);
    }

    public static void remove(String id) {
        Cashier c = searchId(id);
        if (c == null) {
            System.out.println("Error. Cajero no encontrado.");
            return;
        }
        System.out.println(c);
        HumanDB.removeHuman(c);
    }

    public static void list() {
        for (Human human : HumanDB.list()) {
            if (human instanceof Cashier) {
                System.out.println(human.toString());
            }
        }
    }


    public static Cashier searchId(String id) {
        return (Cashier) HumanDB.findId(id);
    }

    public static void cashTickets(String cashId) {
        System.out.println("Tickets:");

        Cashier aux = (Cashier) HumanDB.findId(cashId);
        for (String t : aux.getTickets()) {
            System.out.println(TicketDB.findId(t));
        }
    }

    public static String generarId() {
        return "UW" + (1_000_000 + new Random().nextInt(9_000_000));
    }
}

