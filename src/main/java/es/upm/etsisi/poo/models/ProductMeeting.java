package es.upm.etsisi.poo.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class ProductMeeting extends Product {

    private LocalDate date;
    private int maxParticipantes;
    private boolean isFood; //true==comida, false==reunion
    private int asistentes;

    public ProductMeeting(int id, String name, double price, LocalDate date, int maxParticipantes, boolean isFood) {
        super(id, name, null, price);
        try {
            this.date = date;
            if (maxParticipantes <= 100)
                this.maxParticipantes = maxParticipantes;
            else {
                this.maxParticipantes = 100;
                System.out.println("Se ha establecido el máximo de asistentes en 100, el máximo permitido");
            }
            this.isFood = isFood;
            this.asistentes = 0;
        } catch (DateTimeParseException ex) {
            System.out.println("El formato de la fecha debe ser yyyy-MM-dd");
        }

    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean setAsistentes(int asistentes) {
        if (asistentes > maxParticipantes) {
            System.out.println("No se pueden añadir mas de " + this.maxParticipantes+" participantes." +
                    " Intentalo de nuevo con un numero valido de integrantes");
            return false;
        } else {
            this.asistentes = asistentes;
            return true;
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

    @Override
    public double getPrice() {
        return price * asistentes;
    }

    @Override
    public String toString() {
        if(asistentes == 0)
        return "{class:" + ((isFood) ? "Food" : "Meeting") +
                ", id:" + id + ", name:'" + name + "', price:" + price * asistentes + ", date of Event:" + date +
                ", max people allowed:" + maxParticipantes + "}";
        else
            return "{class:" + ((isFood) ? "Food" : "Meeting") +
                    ", id:" + id + ", name:'" + name + "', price:" + price * asistentes + ", date of Event:" + date +
                    ", max people allowed:" + maxParticipantes + ", actual people in event:"+ asistentes + "}";
    }

    @Override
    public ProductMeeting clone() {
        return new ProductMeeting(id, name, price, date, maxParticipantes, isFood);
    }
}
