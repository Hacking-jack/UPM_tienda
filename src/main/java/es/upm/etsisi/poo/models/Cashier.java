package es.upm.etsisi.poo.models;

import java.util.Random;

public class Cashier extends Users {

    private String id;

    public Cashier(String id, String nombre, String email) {
        super(nombre, email);
        if (id == null || id.isEmpty()) {
            this.id = generarId();
        } else {
            this.id = id;
        }
    }

    private String generarId() {
        Random r = new Random();
        int num = r.nextInt(10_000_000);
        return "UW" + String.format("%07d", num);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Cash{identifier='" + id + "', name='" + nombre + "', email='" + email + "'}";
    }
}
