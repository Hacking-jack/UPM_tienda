package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.BASES_DE_DATOS.ProductDB;
import es.upm.etsisi.poo.models.Categories;
import es.upm.etsisi.poo.models.Product;
import es.upm.etsisi.poo.models.ProductCustom;

public class ProductCustomController extends ProductController{

    //ESTO SE HACE EN EL PRODUCT CONTROLLER NORMAL, SOLO LE CAMBIAS EL NOMBRE AL METODO NO HACE FALTA EXTENDER NADA.

    public static void add(int id, String name, String categories, double price, int maxPers) {
        if(categorieControl(categories)){
            if (ProductDB.findId(id) != null) {
                System.out.println("Error. Ya existe el id.");
            } else {
                if (ProductDB.getCounter()<200) {
                    Product product = new ProductCustom(name, price, Categories.valueOf(categories), maxPers);
                    ProductDB.addProduct(product);
                    System.out.println(product.toString());
                }else{
                    System.out.println("Catálogo de productos lleno.");
                }
            }
        }
    }
}
