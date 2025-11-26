package es.upm.etsisi.poo.models;

import es.upm.etsisi.poo.BASES_DE_DATOS.ProductDB;

public class ProductCustom extends Product {

    public int maxPers;
    private String[] listaPers;
    private int persAct;

    public ProductCustom(String name, double price, Categories categories, int maxPers, int id) {
        super(id, name, categories,price);
        this.maxPers = maxPers;
        listaPers = new String[this.maxPers];
        this.persAct = 0;
    }

    public void addPers(String[] pers) {
        if (pers.length <= maxPers) {
            listaPers = pers;
            persAct = pers.length;
        } else {
            System.out.println("No se ha podido añadir la personalización " + pers + ", se ha alcanzado el maximo " +
                    "de personalizaciones");
        }
    }

    @Override
    public double getPrice() {
        return price + (0.1 * price * persAct);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{class:ProductPersonalized" +
                ", id:" + id + ", name:'" + name +  "', category:" + categories +
                ", price:" + price + ", maxPersonal:" + maxPers + "}");
        if (persAct > 0) {
            s.append("personalizationList:[");
            for (int i = 0; i < persAct; i++) {
                s.append(listaPers[i]);
                if (i != persAct - 1) {
                    s.append(", ");
                }
            }
            s.append("]}");
        }
        return s.toString();
    }

    @Override
    public ProductCustom clone() {
        ProductCustom p = new ProductCustom(this.name, this.price, this.categories, this.maxPers, this.id);
        ProductDB.addProductClone(p);
        return p;
    }
}
