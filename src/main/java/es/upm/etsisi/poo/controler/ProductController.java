package es.upm.etsisi.poo.controler;

import java.util.ArrayList;


import es.upm.etsisi.poo.dataBase.ProductDB;
import es.upm.etsisi.poo.models.product.Categories;
import es.upm.etsisi.poo.models.product.ProductBasic;
import es.upm.etsisi.poo.models.product.ProductMeetingFood;

public class ProductController {

    public static void add(int id, String name, String categories, double price) {
        if (categorieControl(categories)) {
            if (ProductDB.existeId(id)) {
                System.out.println("Error. Ya existe el id.");
            } else {
                if (ProductDB.countProduct() < 200) {
                    ProductBasic productBasic = new ProductBasic(id, name, Categories.valueOf(categories), price);
                    ProductDB.addProduct(productBasic);
                    System.out.println(productBasic.toString());
                } else {
                    System.out.println("Catálogo de productos lleno.");
                }
            }
        }
    }


    public static void list() {
        System.out.println("Catalog:");
        ArrayList<ProductBasic> productBasics = ProductDB.listProducts();
        for (ProductBasic p : productBasics) {
            System.out.println("  " + p.toString());
        }
    }

    public static void update(int id, String campo, String valor) {

        ProductBasic p = ProductDB.findId(id);
        switch (campo.toUpperCase()) {
            case "NAME":
                p.setName(valor);
                break;
            case "CATEGORY":
                if (p instanceof ProductMeetingFood) {
                    System.out.println("No se puede aplicar una categoría a comidas o reuniones");
                } else {
                    if (categorieControl(valor)) {
                        Categories categorie = Categories.valueOf(valor.toUpperCase());
                        p.setCategories(categorie);
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
        System.out.println(p.toString());

    }

    public static void remove(int id) {
        ProductBasic p = ProductDB.findId(id);
        System.out.println(p.toString());
        ProductDB.removeProduct(p);
    }

    public static ProductBasic findId(int id) {
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
