package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.models.Cashier;

public class HumanController {

    // TODO modificar esto similar a product
    public void addCashier(String id, String nombre, String email) {

        if (id != null && searchId(id) != null) {
            System.out.println("Error. Ya existe un cajero con ese id.");
            return;
        }

        Cashier nuevo = new Cashier(id, nombre, email);
        cajeros.add(nuevo);
        System.out.println(nuevo);
    }
}
