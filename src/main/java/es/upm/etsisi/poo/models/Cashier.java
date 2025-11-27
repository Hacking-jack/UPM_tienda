package es.upm.etsisi.poo.models;

import java.util.ArrayList;

public class Cashier extends Human {


    public Cashier(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.tickets=new ArrayList<>();
    }


    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cash{identifier='" + id + "', name='" + nombre + "', email='" + email + "'}";
    }
}
