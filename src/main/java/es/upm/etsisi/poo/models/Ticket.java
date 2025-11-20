package es.upm.etsisi.poo.models;


import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Ticket {
    private ArrayList<Product> products;
    private String idTicket;
    private double finalPrice;
    private LocalDateTime date;
    private States estado;



    public Ticket() {
        products = new ArrayList<>();
    }

    public Ticket(String idTicket,LocalDateTime date,
                  List<Product> products, double finalPrice, States estado) {
        this.products = new ArrayList<>(products);
        this.finalPrice = finalPrice;
        this.idTicket = idTicket;
        this.date = date;
        this.estado = States.VACIO;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public double getFinalPrice() {
        return finalPrice;
    }
    public String getIdTicket() {
        return idTicket;
    }
}
