package es.upm.etsisi.poo.models.user;

import java.util.ArrayList;

public class Client extends User {

    protected final String cajeroAlta;

    public Client(String nombre, String id, String email, String cajeroAlta) {
        this.nombre = nombre;
        this.id = id;
        this.cajeroAlta = cajeroAlta;
        this.email = email;
        tickets = new ArrayList<>();
    }
    public Client(Integer numId, String nombre, String id, String email, String cajeroAlta) {
        this.numId = numId;
        this.nombre = nombre;
        this.id = id;
        this.cajeroAlta = cajeroAlta;
        this.email = email;
        tickets = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        String cashId;
        if (cajeroAlta != null) {
            cashId = cajeroAlta;
        } else {
            cashId = "null";
        }
        return "USER{identifier='" + id + "', name='" + nombre +
                "', email='" + email + "', cash=" + cashId + "}";
    }
}