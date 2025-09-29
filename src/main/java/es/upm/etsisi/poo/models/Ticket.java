package es.upm.etsisi.poo.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ticket {
    private ArrayList<Product> products = new ArrayList<Product>();
    private final LocalDateTime creationDateTime = LocalDateTime.now();
    private String cashier;

    public Ticket(String cashier) {
        this.cashier = cashier;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }
}
