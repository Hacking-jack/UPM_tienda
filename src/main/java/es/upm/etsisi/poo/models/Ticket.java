package es.upm.etsisi.poo.models;



import java.time.LocalDateTime;
import java.util.ArrayList;


public class Ticket { //Falta limite de 100
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
