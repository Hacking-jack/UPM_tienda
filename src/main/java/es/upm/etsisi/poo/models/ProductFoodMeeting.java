package es.upm.etsisi.poo.models;

public class ProductFoodMeeting extends Product{

    private String fechaCaducidad;
    private int maxParticipantes=100;
    private boolean isFood; //false==comida, true==reunion

    public ProductFoodMeeting(String name, double price, Categories categories, int id, String fechaCaducidad, int maxParticipantes, boolean isFood){
        super(name, price, categories, id);
        this.fechaCaducidad=fechaCaducidad;
        if(maxParticipantes<=100){
            this.maxParticipantes=maxParticipantes;
        }else{
            System.out.println("Se ha establecido el máximo de asistentes en 100, el máximo permitido");
        }
        this.isFood=isFood;
    }
}
