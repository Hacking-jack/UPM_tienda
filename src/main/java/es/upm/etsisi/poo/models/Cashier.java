package es.upm.etsisi.poo.models;

import java.util.Random;

public class Cashier extends Human {


    public Cashier(String nombre, String email) {
        this.id = "UW" + (1_000_000 + new Random().nextInt(9_000_000));
        this.nombre = nombre;
        this.email = email;
    }


    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cash{identifier='" + id + "', name='" + nombre + "', email='" + email + "'}";
    }
}
