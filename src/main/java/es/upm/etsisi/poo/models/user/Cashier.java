package es.upm.etsisi.poo.models.user;

import java.util.ArrayList;

public class Cashier extends User {


    public Cashier(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        tickets = new ArrayList<>();
    }


    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cash{identifier='" + id + "', name='" + nombre + "', email='" + email + "'}";
    }
}
