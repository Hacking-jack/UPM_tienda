package es.upm.etsisi.poo.models;



import java.time.LocalDateTime;
import java.util.ArrayList;


public class Ticket {
    private ArrayList<Product> products;



    public Ticket() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
