package es.upm.etsisi.poo.models;

import es.upm.etsisi.poo.BASES_DE_DATOS.HumanDB;

public class Client extends Human {

    private String cajeroAlta;

    public Client(String nombre, String dni, String email, String cajeroAlta) {
        this.nombre=nombre;
        this.id = dni;
        this.cajeroAlta = cajeroAlta;
        this.email = email;
    }

    public String getId() {
        return this.id;
    }

    public Cashier getCajeroAlta() {
        Human p = HumanDB.findId(cajeroAlta);
        if (p instanceof Cashier) {
            return (Cashier) p;
        }
        return null;
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
