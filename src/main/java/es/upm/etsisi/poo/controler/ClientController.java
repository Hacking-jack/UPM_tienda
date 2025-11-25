package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.BASES_DE_DATOS.HumanDB;
import es.upm.etsisi.poo.models.Cashier;
import es.upm.etsisi.poo.models.Human;
import es.upm.etsisi.poo.models.Client;

import java.util.Comparator;

// Unificaria esta clase con Human Controller, al igual que Cashier son hijos de Human
// Mirar uso de "instance of"
// Mirar ejemplo de uso en ProductControler
public class ClientController {

    public static void add(String nombre, String dni, String email, String cashId) {
        if (HumanDB.findId(dni) != null) {
            System.out.println("Error. Ya existe un cliente con ese DNI.");
            return;
        }
        if (HumanDB.findId(cashId) == null) {
            System.out.println("Error. Cajero no encontrado.");
            return;
        }
        Client u = new Client(nombre,dni,email,cashId);
        HumanDB.addUser(u);
        System.out.println(u);
    }

    public static void remove(String id) {

        Human p = HumanDB.findId(id);
        if (p == null) {
            System.out.println("Error. No existe ese cliente.");
            return;
        }

        System.out.println(p);
        HumanDB.removeHuman(p);
    }

    public static void list() {
        for (Human human:HumanDB.list()) {
            if(human instanceof Client){
                System.out.println(human.toString());
            }
        }
    }

    public static Client searchId(String id) {
        return (Client) HumanDB.findId(id);
    }

}

//o client add "<nombre>" <DNI> <email> <cashId>
//o client remove <DNI>
//o client list ( incluye el dato del cash que lo creo y ordenados por nombre)
