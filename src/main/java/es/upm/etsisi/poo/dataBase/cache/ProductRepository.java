package es.upm.etsisi.poo.dataBase.cache;

import es.upm.etsisi.poo.exceptions.product.ProductNotFoundException;
import es.upm.etsisi.poo.models.product.Product;

import java.util.ArrayList;
//TODO cache de base de datos
public class ProductRepository {
    //Equivalente a una tabla Products en SQL
    private static final ArrayList<Product> PRODUCTS = new ArrayList<>();

    //Equivalente a un insert
    public static void addProduct(Product p) {
        PRODUCTS.add(p);
    }

    public static void addProductClone(Product p) {
        PRODUCTS.add(p);
    }

    //Equivalente a Select * from Products where id = id;
    public static Product findId(String id) {
        for (Product p : PRODUCTS) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new ProductNotFoundException(id);
    }

    public static boolean existeId(String id) {
        for (Product p : PRODUCTS) {
            if (p.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    //Equivalente  DELETE FROM table_name WHERE id = id;
    public static void removeProduct(Product p) {
        PRODUCTS.remove(p);
    }

    //Equivalente SELECT COUNT(*) FROM products
    public static int countProduct() {
        return PRODUCTS.size();
    }

    //Equivalente a Select * from products;
    public static ArrayList<Product> listProducts() {
        return PRODUCTS;
    }

}
