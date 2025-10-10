package es.upm.etsisi.poo.models;



import java.util.ArrayList;


public class Ticket {
    private ArrayList<Product> products;



    public Ticket() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
