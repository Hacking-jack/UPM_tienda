package es.upm.etsisi.poo.models.product;

import es.upm.etsisi.poo.dataBase.ProductDB;

public class ProductBasicCustom extends ProductBasic {

    public final int maxPers;
    private String[] listaPers;
    private int persAct;

    public ProductBasicCustom(String name, double price, Categories categories, int maxPers, int id) {
        super(id, name, categories, price);
        this.maxPers = maxPers;
        listaPers = new String[this.maxPers];
        this.persAct = 0;
    }

    public void addPers(String[] pers) {
        if (pers.length <= maxPers) {
            listaPers = pers;
            persAct = pers.length;
        } else {
            System.out.println("No se ha podido añadir las personalizaciones se ha alcanzado el máximo " +
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
                ", id:" + this.id + ", name:'" + this.name + "', category:" + this.categories +
                ", price:" + this.price + (0.1 * this.price * this.persAct) + ", maxPersonal:" + this.maxPers);
        if (this.persAct > 0) {
            s.append(", personalizationList:[");
            for (int i = 0; i < this.persAct; i++) {
                s.append(this.listaPers[i]);
                if (i != this.persAct - 1) {
                    s.append(", ");
                }
            }
            s.append("]");
        }
        s.append("}");
        return s.toString();
    }

    @Override
    public ProductBasicCustom clone() {
        ProductBasicCustom p = new ProductBasicCustom(this.name, this.price, this.categories, this.maxPers, this.id);
        ProductDB.addProductClone(p);
        return p;
    }
}
