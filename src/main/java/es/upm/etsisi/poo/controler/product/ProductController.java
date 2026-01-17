package es.upm.etsisi.poo.controler.product;

import java.util.ArrayList;


import es.upm.etsisi.poo.dataBase.cache.ProductRepository;
import es.upm.etsisi.poo.exceptions.product.*;
import es.upm.etsisi.poo.models.product.*;

public class ProductController {

    public static void add(String id, String name, String categories, double price) {
        if (categorieControl(categories)) {
            if (ProductRepository.existeId(id)) {
                throw new DuplicateProductIdException();
            } else {
                if (ProductRepository.countProduct() < 200) {
                    Product product = new ProductBasic(id, name, Categories.valueOf(categories), price);
                    ProductRepository.addProduct(product);
                    System.out.println(product);
                } else {
                    throw new FullProductCatalogException();
                }
            }
        }
    }


    public static void list() {
        System.out.println("Catalog:");
        ArrayList<Product> products = ProductRepository.listProducts();
        for (Product p : products) {
            if(p instanceof ProductMeetingFood){
                System.out.println("  " + ((ProductMeetingFood) p).initialString());
            }else {
                System.out.println("  " + p.toString());
            }
        }
    }

    public static void update(String id, String campo, String valor) {

        Product p = ProductRepository.findId(id);
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
                throw new NotValidFieldException(campo);
        }
        System.out.println(p);

    }

    public static void remove(String id) {
        Product p = ProductRepository.findId(id);
        System.out.println(p);
        ProductRepository.removeProduct(p);
    }

    public static Product findId(String id) {
        return ProductRepository.findId(id);
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
        if (!ProductRepository.existeId(id)) {
            return id;
        } else {
            return generarId();
        }
    }

}
