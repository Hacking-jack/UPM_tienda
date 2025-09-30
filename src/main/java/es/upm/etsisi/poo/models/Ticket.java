package es.upm.etsisi.poo.models;

import com.sun.tools.javac.code.Attribute;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Ticket {
    private static ArrayList<Product> products;
    private final LocalDateTime creationDateTime = LocalDateTime.now();
    private String cashier;


    public Ticket() {
        products = new ArrayList<>();
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



    //Despues decidimos donde va
    public static void add(Product product, int cantida){
        for(int i = 0; i<cantida;i++)
            products.add(product);
    }
    public static void remove(Product product){
        products.remove(product);
    }
    public static void print(){
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).toString());
        }
    }
}
