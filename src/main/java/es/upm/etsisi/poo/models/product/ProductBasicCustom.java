package es.upm.etsisi.poo.models.product;

import es.upm.etsisi.poo.dataBase.ProductDB;
import es.upm.etsisi.poo.exceptions.product.PersonalizationsOutOfBoundsExceptions;

public class ProductBasicCustom extends ProductBasic {

    public final int maxPers;
    private String[] listaPers;
    private int persAct;

    public ProductBasicCustom(String name, double price, Categories categories, int maxPers, int id) {
        super(id, name, categories, price);
        this.maxPers = maxPers;
        listaPers = new String[maxPers];
        persAct = 0;
    }

    public void addPers(String[] pers) {
        if (pers.length <= maxPers) {
            listaPers = pers;
            persAct = pers.length;
        } else {
            throw new PersonalizationsOutOfBoundsExceptions();
        }
    }

    @Override
    public double getPrice() {
        return price + (0.1 * price * persAct);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{class:ProductPersonalized" +
                ", id:" + id + ", name:'" + name + "', category:" + categories +
                ", price:" + price + (0.1 * price * persAct) + ", maxPersonal:" + maxPers);
        if (persAct > 0) {
            s.append(", personalizationList:[");
            for (int i = 0; i < persAct; i++) {
                s.append(listaPers[i]);
                if (i != (persAct - 1)) {
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
        ProductBasicCustom p = new ProductBasicCustom(name, price, categories, maxPers, id);
        ProductDB.addProductClone(p);
        return p;
    }
}
