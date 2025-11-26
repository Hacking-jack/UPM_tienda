package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.BASES_DE_DATOS.ProductDB;
import es.upm.etsisi.poo.models.Categories;
import es.upm.etsisi.poo.models.Product;
import es.upm.etsisi.poo.models.ProductCustom;

public class ProductCustomController extends ProductController {

    //ESTO SE HACE EN EL PRODUCT CONTROLLER NORMAL, SOLO LE CAMBIAS EL NOMBRE AL METODO NO HACE FALTA EXTENDER NADA.

    public static void add(Integer id, String name, String categories, double price, int maxPers) {
        if (categorieControl(categories)) {
            if (ProductDB.existeId(id)) {
                System.out.println("Error. Ya existe el id.");
            } else {
                if (ProductDB.countProduct() < 200) {
                    Product product;
                        product = new ProductCustom(name, price, Categories.valueOf(categories), maxPers, id);
                    ProductDB.addProduct(product);
                    System.out.println(product.toString());
                } else {
                    System.out.println("Catálogo de productos lleno.");
                }
            }
        }
    }
}
