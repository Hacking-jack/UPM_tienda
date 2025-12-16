package es.upm.etsisi.poo.models.human;

import java.util.ArrayList;

public class Client extends Human {

    private final String cajeroAlta;

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
        if (cajeroAlta != null) {
            cashId = cajeroAlta;
        } else {
            cashId = "null";
        }
        return "Client{identifier='" + this.id + "', name='" + nombre + "', email='" + email + "', cash=" + cashId + "}";
    }
}
