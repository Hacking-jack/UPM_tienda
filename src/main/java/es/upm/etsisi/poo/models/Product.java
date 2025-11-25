package es.upm.etsisi.poo.models;


import es.upm.etsisi.poo.BASES_DE_DATOS.ProductDB;

import java.util.Random;
import java.util.UUID;

public class Product {

    protected String name;
    protected Categories categories;
    protected double price;
    protected int id;

    public Product(String name, Categories categories, double price, int id) {
        this.name = name;
        this.categories = categories;
        this.price = price;
        this.id = id;
    }

    public Product(String name, double price, Categories categories) {
        this.id = 10000000 + new Random().nextInt(90000000);
        this.name = name;
        this.price = price;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public Product clone() {
        return new Product(this.name, this.categories, this.price, this.id);
    }

    @Override
    public String toString() {
        return "{class:Product, " + "id:" + id + ", name:'"
                + name + '\'' + ", category:" + categories + ", price:" + price + '}';
    }
}
