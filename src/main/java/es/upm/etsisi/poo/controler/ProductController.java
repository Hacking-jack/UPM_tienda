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

    public void add(int id, String name, String categories, double price) {
        if (categorieControl(categories)) {
            if (ProductDB.findId(id) != null) {
                System.out.println("Error. Ya existe el id.");
            } else {
                if (ProductDB.countProduct() < 200) {
                    Product product = new Product(name, Categories.valueOf(categories), price, id);
                    ProductDB.addProduct(product);
                    System.out.println(product.toString());
                } else {
                    System.out.println("Catálogo de productos lleno.");
                }
            }
        }
    }
    // TODO: Mirar Clase de PoductDB para entender su uso
    public void list() {
        System.out.println("Catalog:");
        ArrayList<Product> products = ProductDB.listProducts();
        for (Product p : products)
        {
            System.out.println(p.toString());
        }
    }

    public void update(int id, String campo, String valor) {

        Product p = ProductDB.findId(id);
        if ( p == null) {
            System.out.println("Error. No existe el id para actualizar.");
        } else {
            switch (campo.toUpperCase()) {
                case "NAME":
                    p.setName(valor);
                    break;
                case "CATEGORY":
                    if(categorieControl(valor)) {
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

    public void remove(int id) {
        Product p = ProductDB.findId(id);
        if (p == null) {
            System.out.println("Error. No existe el id para borrar.");
        } else {
            System.out.println(p.toString());
            ProductDB.removeProduct(p);
        }
    }



    public boolean categorieControl(String categorie){
        try{
            categorie=categorie.toUpperCase();
            Categories.valueOf(categorie);
            return true;
        }catch(IllegalArgumentException ex){
            System.out.println("Categoría no válida");
            return false;
        }
    }

    public int generarId(){
        int id =  (int) (Math.random()*9999);
        if(ProductDB.findId(id)==null){
            return id;
        }else{
            return generarId();
        }
    }

}
