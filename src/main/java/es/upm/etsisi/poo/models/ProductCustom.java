package es.upm.etsisi.poo.models;

public class ProductCustom extends Product{

    int maxText;
    int[] texts;

    public ProductCustom(String name, double price, Categories categories, int id, int maxText){
        super(name, price, categories, id);
        this.maxText=maxText;
        texts=new int[this.maxText];
    }
}
