package es.upm.etsisi.poo.models;

public abstract class Users {

    protected String nombre;
    protected String email;

    public Users(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }

    public abstract String getId();
}
