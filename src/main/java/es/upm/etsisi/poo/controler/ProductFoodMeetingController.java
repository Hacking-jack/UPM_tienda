package es.upm.etsisi.poo.controler;


import es.upm.etsisi.poo.dataBase.ProductDB;
import es.upm.etsisi.poo.models.Product;
import es.upm.etsisi.poo.models.ProductMeeting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ProductFoodMeetingController extends ProductController {


    //TODO UNIFICAR ESTO CON PRODUT CONTROLLER, SOLO ES HERENCIA.
    public static void add(int id, String name, double price, String date, int maxParticipantes, boolean isFood) {
        LocalDate fecha = LocalDate.parse(date);
        int dias = (int) ChronoUnit.DAYS.between(LocalDate.now(), fecha);
        if (ProductDB.existeId(id)) {
            System.out.println("Error. Ya existe el id.");
        } else {
            if (ProductDB.countProduct() < 200) {
                if ((isFood && dias >= 3) || (!isFood && (dias >= 1 || (dias == 0 && LocalDateTime.now().getHour() <= 12)))) {
                    Product product = new ProductMeeting(id, name, price, fecha, maxParticipantes, isFood);
                    ProductDB.addProduct(product);
                    System.out.println(product.toString());
                } else {
                    if (isFood) {
                        System.out.println("No cumple el requisito minimo de tiempo, para una comida el tiempo es de 3 dias");
                    } else {
                        System.out.println("No cumple el requisito minimo de tiempo, para una reunion el tiempo es de 12 horas");
                    }
                }
            } else {
                System.out.println("Catálogo de productos lleno.");
            }
        }
    }
}
