package es.upm.etsisi.poo.models;

import com.sun.tools.javac.code.Attribute;

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



    //Despues decidimos donde va
    public void add(Product product, int cantidad){
        for(int i = 0; i<cantidad;i++)
            products.add(product);
    }
    public void remove(Product product){
        products.remove(product);
    }
    public void print(){ // completar
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).toString());
        }
    }
}
