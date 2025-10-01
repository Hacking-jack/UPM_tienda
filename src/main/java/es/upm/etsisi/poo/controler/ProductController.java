package es.upm.etsisi.poo.controler;

import java.util.ArrayList;

import es.upm.etsisi.poo.models.Product;

/*
prod add <id> "<nombre>" <categoria> <precio> (agrega un producto con nuevo id)
prod list (lista productos actuales)
prod update <id> campo valor (campos: nombre|categoria|precio)
prod remove <id>
 */
public class ProductController {

    private ArrayList<Product> products;

    public ProductController() {
        products = new ArrayList<>();
    }


    public void add(int id, String name, String categories, double price) {
        if (existeId(id) != -1) {
            System.out.println("Error. Ya existe el id.");
        } else {
            Product product = new Product(name, price, categories, id);
            products.add(product);
            System.out.println(product.toString());
        }
        //Falta app.echo
    }

    public void list() {
        System.out.println("Catalog:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i).toString());
        }
    }

    public void update(int id, String campo, String valor) {
        int position = existeId(id);
        if (existeId(id) == -1) {
            System.out.println("Error. No existe el id para actualizar.");
        } else {
            switch (campo) {
                case "NAME":
                    products.get(position).setName(valor);
                    break;
                case "CATEGORY":
                    products.get(position).setCategories(valor);
                    break;
                case "PRICE":
                    products.get(position).setPrice(Double.parseDouble(valor));
                    break;
                default:
                    System.out.println("Campo no valido");
                    break;
            }
        }
    }

    public void remove(int id) {
        int position = existeId(id);
        if (position == -1) {
            System.out.println("Error. No existe el id para borrar.");
        } else {
            products.remove(products.get(position));
        }
    }

    public int existeId(int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado && i < products.size()) {
            if (products.get(i).getId() == id) {
                encontrado = true;
            }
        }
        if (!encontrado) {
            i = -1;
        }
        return i;
    }

}
