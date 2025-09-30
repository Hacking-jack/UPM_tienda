package es.upm.etsisi.poo.models;

public class Product {
    private String name;
    private String categories;
    private double price;
    private int id;

    public Product(String name, double price, String categories, int id) {
        this.name = name;
        this.price = price;
        this.categories = categories;
        this.id=id;
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

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{class:Product," + "id:" + id + ", name:'"
                + name + '\''+", category:" + categories +", price:"+ price + '}';
    }
}
