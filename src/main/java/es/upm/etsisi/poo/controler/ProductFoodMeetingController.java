package es.upm.etsisi.poo.controler;


import es.upm.etsisi.poo.BASES_DE_DATOS.ProductDB;
import es.upm.etsisi.poo.models.Product;
import es.upm.etsisi.poo.models.ProductMeeting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ProductFoodMeetingController extends ProductController{


// UNIFICAR ESTO CON PRDUT CONTROLLER, SOLO ES HERENCIA.
    public static void add(int id, String name, String categories, double price, String date, int maxParticipantes, boolean isFood) {
        LocalDate fecha=LocalDate.parse(date);
        int dias= (int) ChronoUnit.DAYS.between(LocalDate.now(), fecha);
        if(categorieControl(categories)){
            if (ProductDB.findId(id)==null) {
                System.out.println("Error. Ya existe el id.");
            } else {
                if (ProductDB.getCounter()<200) {
                    if((isFood && dias>=3) || (!isFood && (dias>=1 || (dias==0 && LocalDateTime.now().getHour()<=12)))) {
                        Product product = new ProductMeeting(id,name, price, fecha, maxParticipantes, isFood);
                        ProductDB.addProduct(product);
                        System.out.println(product.toString());
                    }
                }else{
                    System.out.println("Catálogo de productos lleno.");
                }
            }
        }
    }
}
