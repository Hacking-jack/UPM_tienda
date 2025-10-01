package es.upm.etsisi.poo.models;

public class Product {
    public enum Categorie {
        MERCH,
        PAPELERIA,
        ROPA,
        LIBRO,
        ELECTRONICA;
    }

    private String name;
    private Categorie categories;
    private double price;
    private int id;

    public Product(String name, double price, Categorie categories, int id) {
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


    public static Categorie getCategories(String texto) {
        try {
            return Categorie.valueOf(texto.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Categoría inválida: " + texto);
            return null;
        }
    }



    public void setCategories(Categorie categories) {
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
