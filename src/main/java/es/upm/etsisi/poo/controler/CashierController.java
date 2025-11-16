package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.models.Cashier;

import java.util.ArrayList;
import java.util.Comparator;

public class CashierController {

    private ArrayList<Cashier> cajeros;

    public CashierController() {
        this.cajeros = new ArrayList<>();
    }

    public void add(String id, String nombre, String email) {

        if (id != null && searchId(id) != null) {
            System.out.println("Error. Ya existe un cajero con ese id.");
            return;
        }

        Cashier nuevo = new Cashier(id, nombre, email);
        cajeros.add(nuevo);
        System.out.println(nuevo);
    }

    public void remove(String id) {
        Cashier c = searchId(id);
        if (c == null) {
            System.out.println("Error. Cajero no encontrado.");
            return;
        }

        System.out.println(c);
        cajeros.remove(c);
    }

    public void list() {
        Comparator<Cashier> comparador = Comparator.comparing(Cashier::getNombre);
        cajeros.sort(comparador);
        for (int i = 0; i < cajeros.size(); i++) {
            Cashier cajero = cajeros.get(i);
            System.out.println(cajero);
        }
    }

    public Cashier searchId(String id) {
        for (int i = 0; i < cajeros.size(); i++) {
            Cashier cajero = cajeros.get(i);
            if (cajero.getId().equals(id)) {
                return cajero;
            }
        }
        return null;
    }
}


//o cash add [<id>] "<nombre>"<email>
//o cash remove <id>
//o cash list (Ordenados por nombre y sin mostrar sus tickets)
//o cash tickets <id> (Muestra los tickets del cajero ordenados por el Id del ticket,
//mostrando solo el ID y el estado)