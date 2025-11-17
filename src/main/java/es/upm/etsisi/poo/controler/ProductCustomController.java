package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.models.Categories;
import es.upm.etsisi.poo.models.Product;
import es.upm.etsisi.poo.models.ProductCustom;

public class ProductCustomController extends ProductController{

    public ProductCustomController(){
        super();
    }


    public void add(int id, String name, String categories, double price, int maxPers) {
        if(categorieControl(categories)){
            if (existeId(id) != -1) {
                System.out.println("Error. Ya existe el id.");
            } else {
                if (counter<200) {
                    Product product = new ProductCustom(name, price, Categories.valueOf(categories), id, maxPers);
                    products.add(product);
                    System.out.println(product.toString());
                    counter++;
                }else{
                    System.out.println("Catálogo de productos lleno.");
                }
            }
        }
    }
}
