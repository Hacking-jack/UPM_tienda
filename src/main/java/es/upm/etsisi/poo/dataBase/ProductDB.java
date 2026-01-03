package es.upm.etsisi.poo.dataBase;

import es.upm.etsisi.poo.exceptions.product.ProductNotFoundException;
import es.upm.etsisi.poo.models.product.ProductBasic;

import java.util.ArrayList;
//TODO cache de base de datos
public class ProductDB {
    //Equivalente a una tabla Products en SQL
    private static final ArrayList<ProductBasic> PRODUCT_BASICS = new ArrayList<>();

    //Equivalente a un insert
    public static void addProduct(ProductBasic p) {
        PRODUCT_BASICS.add(p);
    }

    public static void addProductClone(ProductBasic p) {
        PRODUCT_BASICS.add(p);
    }

    //Equivalente a Select * from Products where id = id;
    public static ProductBasic findId(int id) {
        for (ProductBasic p : PRODUCT_BASICS) {
            if (p.getId() == id) {
                return (p);
            }
        }
        throw new ProductNotFoundException(id);
    }

    public static boolean existeId(int id) {
        for (ProductBasic p : PRODUCT_BASICS) {
            if (p.getId() == id) {
                return true;
            }
        }
        return false;
    }

    //Equivalente  DELETE FROM table_name WHERE id = id;
    public static void removeProduct(ProductBasic p) {
        PRODUCT_BASICS.remove(p);
    }

    //Equivalente SELECT COUNT(*) FROM products
    public static int countProduct() {
        return PRODUCT_BASICS.size();
    }

    //Equivalente a Select * from products;
    public static ArrayList<ProductBasic> listProducts() {
        return PRODUCT_BASICS;
    }

}
