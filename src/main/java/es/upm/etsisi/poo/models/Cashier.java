package es.upm.etsisi.poo.models;

import java.util.Random;

public class Cashier extends Human {


    public Cashier(String id, String nombre, String email) {
        //TODO revisar que no se repitan ids
        this.id = id;
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
