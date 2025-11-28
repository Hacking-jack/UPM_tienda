package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.BASES_DE_DATOS.HumanDB;
import es.upm.etsisi.poo.models.Cashier;
import es.upm.etsisi.poo.models.Human;
import es.upm.etsisi.poo.models.Client;

import java.util.Comparator;


public class ClientController {

    public static void add(String nombre, String dni, String email, String cashId) {
        if (HumanDB.existeId(dni)) {
            System.out.println("Error. Ya existe un cliente con ese DNI.");
            return;
        }
        if (!HumanDB.existeId(cashId)) {
            System.out.println("Error. Cajero no encontrado.");
            return;
        }
        Client u = new Client(nombre, dni, email, cashId);
        HumanDB.addUser(u);
        System.out.println(u);
    }

    public static void remove(String id) {

        Human p = HumanDB.findId(id);
        if (p == null) {
            System.out.println("Error. No existe ese cliente.");
            return;
        }

        HumanDB.removeHuman(p);
    }

    public static void list() {
        System.out.println("Client:");
        for (Human human : HumanDB.list()) {
            if (human instanceof Client) {
                System.out.println("  "+human.toString());
            }
        }
    }

    public static Client searchId(String id) {
        return (Client) HumanDB.findId(id);
    }

    public static boolean existeId(String id){
        return HumanDB.existeId(id);
    }

}