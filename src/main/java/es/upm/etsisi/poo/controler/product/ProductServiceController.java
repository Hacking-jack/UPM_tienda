package es.upm.etsisi.poo.controler.product;

import es.upm.etsisi.poo.dataBase.cache.ProductRepository;
import es.upm.etsisi.poo.exceptions.product.NotEnoughTimeException;
import es.upm.etsisi.poo.models.product.*;
import java.time.LocalDate;

public class ProductServiceController {
    public static void addService(LocalDate dateMax, String serviceType) {
        if(dateMax.isBefore(LocalDate.now())){
            throw new NotEnoughTimeException();
        }
        int contadorServicios = 0;
        for (Product p : ProductRepository.listProducts()) {
            if (p instanceof ProductService) {
                contadorServicios++;
            }
        }
        int siguienteNum = contadorServicios + 1;
        Services category = Services.valueOf(serviceType.toUpperCase());
        ProductService nuevoServicio = new ProductService(siguienteNum, dateMax, category);
        ProductRepository.addProduct(nuevoServicio);
        System.out.println(nuevoServicio);
    }
}