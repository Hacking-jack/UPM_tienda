package es.upm.etsisi.poo.models.product;


import es.upm.etsisi.poo.exceptions.product.NegativeNumException;

public class ProductBasic extends Product {

    protected String name;
    protected Categories categories;
    protected double price;
    protected final int id;

    public ProductBasic(int id, String name, Categories categories, double price) {
        this.name = name;
        this.categories = categories;
        if (price >= 0) {
            this.price = price;
        } else {
            throw new NegativeNumException();
        }
        this.id = id;
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

    @Override
    public ProductBasic clone() {
        return new ProductBasic(id, name, categories, price);
    }

    @Override
    public String toString() {
        return "{class:Product, " + "id:" + id + ", name:'"
                + name + '\'' + ", category:" + categories + ", price:" + price + '}';
    }
}
