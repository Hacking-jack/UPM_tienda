package es.upm.etsisi.poo.controler;

import java.util.ArrayList;


import es.upm.etsisi.poo.models.Categories;
import es.upm.etsisi.poo.models.Product;

/*
prod add <id> "<nombre>" <categoria> <precio> (agrega un producto con nuevo id)
prod list (lista productos actuales)
prod update <id> campo valor (campos: nombre|categoria|precio)
prod remove <id>
 */
public class ProductController {

    protected ArrayList<Product> products;
    protected int counter;

    public ProductController() {
        products = new ArrayList<>();
        this.counter = 0;
    }



    public void add(int id, String name, String categories, double price) {
        if(categorieControl(categories)){
            if (existeId(id) != -1) {
                System.out.println("Error. Ya existe el id.");
            } else {
                if (counter<200) {
                    Product product = new Product(name, price, Categories.valueOf(categories), id);
                    products.add(product);
                    System.out.println(product.toString());
                    counter++;
                }else{
                    System.out.println("Catálogo de productos lleno.");
                }
            }
        }
    }

    public void list() {
        System.out.println("Catalog:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).toString());
        }
    }

    public void update(int id, String campo, String valor) {
        int position = existeId(id)-1;
        if (existeId(id) == -1) {
            System.out.println("Error. No existe el id para actualizar.");
        } else {
            switch (campo.toUpperCase()) {
                case "NAME":
                    products.get(position).setName(valor);
                    break;
                case "CATEGORY":
                    if(categorieControl(valor)) {
                        Categories categorie = Categories.valueOf(valor.toUpperCase());
                        products.get(position).setCategories(categorie);
                    }
                    break;
                case "PRICE":
                    products.get(position).setPrice(Double.parseDouble(valor));
                    break;
                default:
                    System.out.println("Campo no valido");
                    break;
            }
            System.out.println(products.get(position).toString());
        }
    }

    public void remove(int id) {
        int position = existeId(id)-1;
        if (position == -2) {
            System.out.println("Error. No existe el id para borrar.");
        } else {
            System.out.println(products.get(position).toString());
            products.remove(products.get(position));
            counter--;
        }
    }

    public int existeId(int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado && i < products.size()) {
            if (products.get(i).getId() == id) {
                encontrado = true;
            }
            i++;
        }
        if (!encontrado) {
            i = -1;
        }
        return i;
    }

    public Product productoID(int id){
        if(existeId(id)!=-1)
            return products.get((existeId(id))-1);
        else{
            return null;
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

}
