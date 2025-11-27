package es.upm.etsisi.poo.controler;

import java.util.ArrayList;


import es.upm.etsisi.poo.BASES_DE_DATOS.ProductDB;
import es.upm.etsisi.poo.models.Categories;
import es.upm.etsisi.poo.models.Product;

/*
prod add <id> "<nombre>" <categoria> <precio> (agrega un producto con nuevo id)
prod list (lista productos actuales)
prod update <id> campo valor (campos: nombre|categoria|precio)
prod remove <id>
 */
public class ProductController {

    public static void add(int id, String name, String categories, double price) {
        if (categorieControl(categories)) {
            if (ProductDB.existeId(id)) {
                System.out.println("Error. Ya existe el id.");
            } else {
                if (ProductDB.countProduct() < 200) {
                    Product product = new Product(id, name, Categories.valueOf(categories), price);
                    ProductDB.addProduct(product);
                    System.out.println(product.toString());
                } else {
                    System.out.println("Catálogo de productos lleno.");
                }
            }
        }
    }


    public static void list() {
        System.out.println("Catalog:");
        ArrayList<Product> products = ProductDB.listProducts();
        for (Product p : products) {
            System.out.println("  "+p.toString());
        }
    }

    public static void update(int id, String campo, String valor) {

        Product p = ProductDB.findId(id);
        if (p == null) {
            System.out.println("Error. No existe el id para actualizar.");
        } else {
            switch (campo.toUpperCase()) {
                case "NAME":
                    p.setName(valor);
                    break;
                case "CATEGORY":
                    if (categorieControl(valor)) {
                        Categories categorie = Categories.valueOf(valor.toUpperCase());
                        p.setCategories(categorie);
                    }
                    break;
                case "PRICE":
                    p.setPrice(Double.parseDouble(valor));
                    break;
                default:
                    System.out.println("Campo no valido");
                    break;
            }
            System.out.println(p.toString());
        }
    }

    public static void remove(int id) {
        Product p = ProductDB.findId(id);
        if (p == null) {
            System.out.println("Error. No existe el id para borrar.");
        } else {
            System.out.println(p.toString());
            ProductDB.removeProduct(p);
        }
    }

    public static Product findId(int id) {
        return ProductDB.findId(id);
    }


    public static boolean categorieControl(String categorie) {
        try {
            categorie = categorie.toUpperCase();
            Categories.valueOf(categorie);
            return true;
        } catch (IllegalArgumentException ex) {
            System.out.println("Categoría no válida");
            return false;
        }
    }

    public static int generarId() {
        int id = (int) (Math.random() * 10000);
        if (!ProductDB.existeId(id)) {
            return id;
        } else {
            return generarId();
        }
    }

}
