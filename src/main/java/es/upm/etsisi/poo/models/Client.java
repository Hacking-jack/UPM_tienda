package es.upm.etsisi.poo.models;

public class Client extends Users {
    private String dni;
    private Cashier cajeroAlta;

    public Client(String nombre, String dni, String email, Cashier cajeroAlta) {
        super(nombre, email);
        this.dni = dni;
        this.cajeroAlta = cajeroAlta;
    }

    public String getId() { return dni; }
    public Cashier getCajeroAlta() { return cajeroAlta; }

    @Override
    public String toString() {
        String cashId;
        if (cajeroAlta != null) {
            cashId = cajeroAlta.getId();
        } else {
            cashId = "null";
        }
        return "Client{identifier='" + dni + "', name='" + nombre + "', email='" + email + "', cash=" + cashId + "}";
    }
}
