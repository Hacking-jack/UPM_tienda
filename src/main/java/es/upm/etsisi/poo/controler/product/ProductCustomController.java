package es.upm.etsisi.poo.controler.product;

import es.upm.etsisi.poo.dataBase.ProductDB;
import es.upm.etsisi.poo.exceptions.product.DuplicateProductIdException;
import es.upm.etsisi.poo.exceptions.product.FullProductCatalogException;
import es.upm.etsisi.poo.models.product.Categories;
import es.upm.etsisi.poo.models.product.ProductBasic;
import es.upm.etsisi.poo.models.product.ProductBasicCustom;

public class ProductCustomController extends ProductController {

    //TODO ESTO SE HACE EN EL PRODUCT CONTROLLER NORMAL, SOLO LE CAMBIAS EL NOMBRE AL METODO NO HACE FALTA EXTENDER NADA.

    public static void add(Integer id, String name, String categories, double price, int maxPers) {
        if (categorieControl(categories)) {
            if (ProductDB.existeId(id)) {
                throw new DuplicateProductIdException();
            } else {
                if (ProductDB.countProduct() < 200) {
                    ProductBasic productBasic;
                    productBasic = new ProductBasicCustom(name, price, Categories.valueOf(categories), maxPers, id);
                    ProductDB.addProduct(productBasic);
                    System.out.println(productBasic);
                } else {
                    throw new FullProductCatalogException();
                }
            }
        }
    }
}
