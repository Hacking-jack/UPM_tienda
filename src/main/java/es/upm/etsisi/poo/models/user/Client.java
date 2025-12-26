package es.upm.etsisi.poo.models.user;

import java.util.ArrayList;

public class Client extends User {

    protected final String cajeroAlta;

    public Client(String nombre, String dni, String email, String cajeroAlta) {
        this.nombre = nombre;
        this.id = dni;
        this.cajeroAlta = cajeroAlta;
        this.email = email;
        this.tickets = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        String cashId;
        if (this.cajeroAlta != null) {
            cashId = this.cajeroAlta;
        } else {
            cashId = "null";
        }
        return "USER{identifier='" + this.id + "', name='" + this.nombre +
                "', email='" + this.email + "', cash=" + cashId + "}";
    }
}