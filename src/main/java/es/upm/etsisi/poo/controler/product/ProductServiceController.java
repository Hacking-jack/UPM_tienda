package es.upm.etsisi.poo.controler.product;

import es.upm.etsisi.poo.dataBase.ProductDB;
import es.upm.etsisi.poo.models.product.Product;
import es.upm.etsisi.poo.models.product.ProductService;
import es.upm.etsisi.poo.models.product.Services;
import java.time.LocalDateTime;

public class ProductServiceController {
    public static void addService(LocalDateTime dateMax, String serviceType) {
        int contadorServicios = 0;
        for (Product p : ProductDB.listProducts()) {
            if (p instanceof ProductService) {
                contadorServicios++;
            }
        }
        int siguienteNum = contadorServicios + 1;
        Services category = Services.valueOf(serviceType.toUpperCase());
        ProductService nuevoServicio = new ProductService(siguienteNum, dateMax, category);
        ProductDB.addProduct(nuevoServicio);
        System.out.println("Servicio creado: " + nuevoServicio);
    }
}