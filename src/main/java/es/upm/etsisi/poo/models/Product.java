package es.upm.etsisi.poo.models;


public class Product {


    protected String name;
    protected Categories categories;
    protected double price;
    protected int id;

    public Product(String name, double price, Categories categories, int id) {
        this.name = name;
        this.price = price;
        this.categories = categories;
        this.id=id;
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
    public String toString() {
        return "{class:Product," + "id:" + id + ", name:'"
                + name + '\''+", category:" + categories +", price:"+ price + '}';
    }
}
