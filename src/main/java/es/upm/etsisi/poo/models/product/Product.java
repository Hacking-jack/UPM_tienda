package es.upm.etsisi.poo.models.product;

import es.upm.etsisi.poo.exceptions.product.NegativeNumException;

public abstract class Product implements Cloneable{
    protected String name;
    protected double price;
    protected final String id;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        if (price < 0) {
            throw new NegativeNumException();
        }
        this.price = price;
    }
    protected Product(String idS) {
        this.id = idS;
        //NO PONGO NI NOMBRE NI PRECIO POR QUE LOS SERVICIOS NO LO NECESITAN
    }
    public String getId() {
        return id;
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
        if (price >= 0) {
            this.price = price;
        } else {
            throw new NegativeNumException();
        }
    }
    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
    }
}
