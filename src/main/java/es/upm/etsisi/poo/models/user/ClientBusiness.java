package es.upm.etsisi.poo.models.user;

import java.util.ArrayList;

public class ClientBusiness extends Client {
    public ClientBusiness(String nombre, String nif, String email, String cajeroAlta) {
        super(nombre, nif, email, cajeroAlta);
    }

    @Override
    public String toString() {
        String cashId;
        if (cajeroAlta != null) {
            cashId = cajeroAlta;
        } else {
            cashId = "null";
        }
        return "COMPANY{identifier='" + id + "', name='" + nombre + "', email='" + email + "', cash=" + cashId + "}";
    }

}
