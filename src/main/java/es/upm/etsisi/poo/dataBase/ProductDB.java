package es.upm.etsisi.poo.dataBase;

import es.upm.etsisi.poo.exceptions.ProductNotFound;
import es.upm.etsisi.poo.models.Product;

import java.util.ArrayList;

public class ProductDB {
    //Equivalente a una tabla Products en SQL
    static private final ArrayList<Product> products = new ArrayList<>();

    //Equivalente a un insert
    static public void addProduct(Product p) {
        products.add(p);
    }

    static public void addProductClone(Product p) {
        products.add(p);
    }

    //Equivalente a Select * from Products where id = id;
    static public Product findId(int id) {
        for (Product p : products) {
            if (p.getId() == id)
                return (p);
        }
        throw new ProductNotFound("No se encontró el producto con id " + id);
    }

    static public boolean existeId(int id) {
        for (Product p : products) {
            if (p.getId() == id)
                return true;
        }
        return false;
    }

    //Equivalente  DELETE FROM table_name WHERE id = id;
    static public void removeProduct(Product p) {
        products.remove(p);
    }

    //Equivalente SELECT COUNT(*) FROM products
    static public int countProduct() {
        return products.size();
    }

    //Equivalente a Select * from products;
    static public ArrayList<Product> listProducts() {
        return products;
    }

}
