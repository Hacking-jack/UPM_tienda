package es.upm.etsisi.poo.controler;


import es.upm.etsisi.poo.models.Product;
import es.upm.etsisi.poo.models.ProductFoodMeeting;

public class ProductFoodMeetingController extends ProductController{

    public ProductFoodMeetingController() {
        super();
    }

    public void add(int id, String name, String categories, double price, String date, int maxParticipantes, boolean isFood) {
        if(categorieControl(categories)){
            if (existeId(id) != -1) {
                System.out.println("Error. Ya existe el id.");
            } else {
                if (counter<200) {
                    Product product = new ProductFoodMeeting(name, price, id, date, maxParticipantes, isFood);
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
