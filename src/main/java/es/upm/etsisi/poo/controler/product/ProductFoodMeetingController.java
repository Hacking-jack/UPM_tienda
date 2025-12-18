package es.upm.etsisi.poo.controler.product;


import es.upm.etsisi.poo.dataBase.ProductDB;
import es.upm.etsisi.poo.exceptions.human.DuplicateIdentifierException;
import es.upm.etsisi.poo.exceptions.product.DuplicateProductIdException;
import es.upm.etsisi.poo.exceptions.product.FullProductCatalogException;
import es.upm.etsisi.poo.exceptions.product.NotEnoughTimeException;
import es.upm.etsisi.poo.models.product.ProductBasic;
import es.upm.etsisi.poo.models.product.ProductMeetingFood;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ProductFoodMeetingController extends ProductController {


    public static void add(int id, String name, double price, String date, int maxParticipantes, boolean isFood) {
        LocalDate fecha = LocalDate.parse(date);
        int dias = (int) ChronoUnit.DAYS.between(LocalDate.now(), fecha);
        if (ProductDB.existeId(id)) {
            throw new DuplicateProductIdException("Error, ya existe un producto con ese id");
        } else {
            if (ProductDB.countProduct() < 200) {
                if ((isFood && dias >= 3) || (!isFood && (dias >= 1 || (dias == 0 && LocalDateTime.now().getHour() <= 12)))) {
                    ProductBasic productBasic = new ProductMeetingFood(id, name, price, fecha, maxParticipantes, isFood);
                    ProductDB.addProduct(productBasic);
                    System.out.println(productBasic.toString());
                } else {
                    if (isFood) {
                        throw new NotEnoughTimeException("No cumple el requisito minimo de tiempo, para una comida el tiempo es de 3 dias");
                    } else {
                        throw new NotEnoughTimeException("No cumple el requisito minimo de tiempo, para una reunion el tiempo es de 12 horas");
                    }
                }
            } else {
                throw new FullProductCatalogException("Catálogo de productos lleno.");
            }
        }
    }
}
