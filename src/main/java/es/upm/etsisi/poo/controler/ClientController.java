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

    public void add(String nombre, String dni, String email, String cashId) {
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

    public void remove(String id) {

        Human p = HumanDB.findId(id);
        if (p == null) {
            System.out.println("Error. No existe ese cliente.");
            return;
        }

        System.out.println(p);
        HumanDB.removeHuman(p);
    }

    public void list() {
        for (Human human:HumanDB.list()) {
            if(human instanceof Client){
                System.out.println(human.toString());
            }
        }
    }

    public Client searchId(String id) {
        for (Human h:HumanDB.list()) {
            if(h instanceof Client && h.getId().equals(id)){
                return (Client) h;
            }
        }
        return null;
    }

}

//o client add "<nombre>" <DNI> <email> <cashId>
//o client remove <DNI>
//o client list ( incluye el dato del cash que lo creo y ordenados por nombre)
