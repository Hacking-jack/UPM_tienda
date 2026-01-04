package es.upm.etsisi.poo.controler.product;

import java.util.ArrayList;


import es.upm.etsisi.poo.dataBase.ProductDB;
import es.upm.etsisi.poo.exceptions.product.CategoryMismatchException;
import es.upm.etsisi.poo.exceptions.product.DuplicateProductIdException;
import es.upm.etsisi.poo.exceptions.product.FoodMeetingCategoryException;
import es.upm.etsisi.poo.exceptions.product.FullProductCatalogException;
import es.upm.etsisi.poo.models.product.Categories;
import es.upm.etsisi.poo.models.product.Product;
import es.upm.etsisi.poo.models.product.ProductBasic;
import es.upm.etsisi.poo.models.product.ProductMeetingFood;

public class ProductController {

    public static void add(String id, String name, String categories, double price) {
        if (categorieControl(categories)) {
            if (ProductDB.existeId(id)) {
                throw new DuplicateProductIdException();
            } else {
                if (ProductDB.countProduct() < 200) {
                    Product product = new ProductBasic(id, name, Categories.valueOf(categories), price);
                    ProductDB.addProduct(product);
                    System.out.println(product);
                } else {
                    throw new FullProductCatalogException();
                }
            }
        }
    }


    public static void list() {
        System.out.println("Catalog:");
        ArrayList<Product> products = ProductDB.listProducts();
        for (Product p : products) {
            System.out.println("  " + p.toString());
        }
    }

    public static void update(String id, String campo, String valor) {

        Product p = ProductDB.findId(id);
        switch (campo.toUpperCase()) {
            case "NAME":
                p.setName(valor);
                break;
            case "CATEGORY":
                if (p instanceof ProductMeetingFood) {
                    throw new FoodMeetingCategoryException();
                } else {
                    if (categorieControl(valor)) {
                        Categories categorie = Categories.valueOf(valor.toUpperCase());
                        ((ProductBasic) p).setCategories(categorie);
                    }
                }
                break;
            case "PRICE":
                p.setPrice(Double.parseDouble(valor));
                break;
            default:
                System.out.println("Campo no valido");
                break;
        }
        System.out.println(p);

    }

    public static void remove(String id) {
        Product p = ProductDB.findId(id);
        System.out.println(p);
        ProductDB.removeProduct(p);
    }

    public static Product findId(String id) {
        return ProductDB.findId(id);
    }


    public static boolean categorieControl(String categorie) {
        try {
            categorie = categorie.toUpperCase();
            Categories.valueOf(categorie);
            return true;
        } catch (IllegalArgumentException ex) {
            throw new CategoryMismatchException();
        }
    }

    public static String generarId() {
        int numId = (int) (Math.random() * 10000);
        String id = String.valueOf(numId);
        if (!ProductDB.existeId(id)) {
            return id;
        } else {
            return generarId();
        }
    }

}
