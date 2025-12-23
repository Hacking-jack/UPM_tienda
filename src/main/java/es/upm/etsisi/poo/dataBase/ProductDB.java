package es.upm.etsisi.poo.dataBase;

import es.upm.etsisi.poo.exceptions.product.ProductNotFoundException;
import es.upm.etsisi.poo.models.product.ProductBasic;

import java.util.ArrayList;

public class ProductDB {
    //Equivalente a una tabla Products en SQL
    static private final ArrayList<ProductBasic> PRODUCT_BASICS = new ArrayList<>();

    //Equivalente a un insert
    static public void addProduct(ProductBasic p) {
        PRODUCT_BASICS.add(p);
    }

    static public void addProductClone(ProductBasic p) {
        PRODUCT_BASICS.add(p);
    }

    //Equivalente a Select * from Products where id = id;
    static public ProductBasic findId(int id) {
        for (ProductBasic p : PRODUCT_BASICS) {
            if (p.getId() == id)
                return (p);
        }
        throw new ProductNotFoundException(id);
    }

    static public boolean existeId(int id) {
        for (ProductBasic p : PRODUCT_BASICS) {
            if (p.getId() == id)
                return true;
        }
        return false;
    }

    //Equivalente  DELETE FROM table_name WHERE id = id;
    static public void removeProduct(ProductBasic p) {
        PRODUCT_BASICS.remove(p);
    }

    //Equivalente SELECT COUNT(*) FROM products
    static public int countProduct() {
        return PRODUCT_BASICS.size();
    }

    //Equivalente a Select * from products;
    static public ArrayList<ProductBasic> listProducts() {
        return PRODUCT_BASICS;
    }

}
