package es.upm.etsisi.poo.models.product;

import es.upm.etsisi.poo.exceptions.product.NegativeNumException;

//TODO comprobar todos los ids (id de productos sea P001, que los meetings sean M001, las comidas F001 ...)
public abstract class Product implements Cloneable{
    protected String name;
    protected double price;
    protected final int id;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        if (price < 0) {
            throw new NegativeNumException();
        }
        this.price = price;
    }

    public int getId() {
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
    protected Product clone() throws CloneNotSupportedException {
        // Shallow copy automática de todos los campos en Product
        return (Product) super.clone();
    }
}
