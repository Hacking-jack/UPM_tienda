package es.upm.etsisi.poo.controler.product;

import es.upm.etsisi.poo.dataBase.cache.ProductRepository;
import es.upm.etsisi.poo.exceptions.product.DuplicateProductIdException;
import es.upm.etsisi.poo.exceptions.product.FullProductCatalogException;
import es.upm.etsisi.poo.models.product.*;

public class ProductCustomController extends ProductController {

    //TODO ESTO SE HACE EN EL PRODUCT CONTROLLER NORMAL, SOLO LE CAMBIAS EL NOMBRE AL METODO NO HACE FALTA EXTENDER NADA.

    public static void add(String id, String name, String categories, double price, int maxPers) {
        if (categorieControl(categories)) {
            if (ProductRepository.existeId(id)) {
                throw new DuplicateProductIdException();
            } else {
                if (ProductRepository.countProduct() < 200) {
                    ProductBasic productBasic;
                    productBasic = new ProductBasicCustom(name, price, Categories.valueOf(categories), maxPers, id);
                    ProductRepository.addProduct(productBasic);
                    System.out.println(productBasic);
                } else {
                    throw new FullProductCatalogException();
                }
            }
        }
    }
}
