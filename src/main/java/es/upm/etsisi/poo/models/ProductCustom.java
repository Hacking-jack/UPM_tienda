package es.upm.etsisi.poo.models;

public class ProductCustom extends Product{

    int maxPers;
    int[] texts;

    public ProductCustom(String name, double price, Categories categories, int id, int maxPers){
        super(name, price, categories, id);
        this.maxPers = maxPers;
        texts=new int[this.maxPers];
    }

    @Override
    public String toString() {
        return "{class:ProductPersonalized" +
                ", id:" + id + ", name:" + name + ", price:" + price + ", category:" + categories +
                ", price:" + price + ", maxPersonal:" + maxPers + "}";
    }
}
