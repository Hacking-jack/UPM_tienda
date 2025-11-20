package es.upm.etsisi.poo.models;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ProductFoodMeeting extends Product{

    private LocalDate date;
    private int maxParticipantes=100;
    private boolean isFood; //false==comida, true==reunion
    private int asistentes;

    public ProductFoodMeeting(String name, double price, int id, LocalDate date, int maxParticipantes, boolean isFood){
        super(name, price, null, id);
        try{
            this.date = date;
            if(maxParticipantes<=100){
                this.maxParticipantes=maxParticipantes;
            }else{
                System.out.println("Se ha establecido el máximo de asistentes en 100, el máximo permitido");
            }
            this.isFood=isFood;
            this.asistentes=0;
        }catch (DateTimeParseException ex){
            System.out.println("El formato de la fecha debe ser yyyy-MM-dd");
        }

    }

    public void añadirAsistentes(int asistentes){
        if(this.asistentes+asistentes>maxParticipantes){
            System.out.println("No se han podido añadir todos los asistentes, se han añadido hasta llegar al maximo");
            this.asistentes=maxParticipantes;
        }else{
            this.asistentes+=asistentes;
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    public boolean isFood() {
        return isFood;
    }

    public int getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(int asistentes){
        this.asistentes=asistentes;
    }

    @Override
    public double getPrice(){
        return price*asistentes;
    }

    @Override
    public String toString() {
        return "{class:" + ((isFood) ? "Food" : "Meeting") +
                ", id:" + id + ", name:" + name + ", price:" + price * asistentes + ", date of Event:" + date +
                ", max people allowed:" + maxParticipantes+"}";
    }
}

//prod add [<id>] "<name>" <category> <price> [<maxPers>]
// si tiene <maxPers> se considerara que el producto es personalizable)
//o prod update <id> NAME|CATEGORY|PRICE <value>
//o prod addFood [<id>] "< name>" <price> <expiration: yyyy-MM-dd> <max_people>
// El precio es por persona apuntada
//o prod addMeeting [<id>] "<name>" <price> < expiration: yyyy-MM-dd> < max_people >
// El precio es por persona apuntada
