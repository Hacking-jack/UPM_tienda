package es.upm.etsisi.poo.models.product;

import es.upm.etsisi.poo.exceptions.product.MaxAssistantOutOfBounds;
import es.upm.etsisi.poo.exceptions.product.NegativeNumException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ProductMeetingFood extends ProductBasic {

    private LocalDate date;
    private int maxParticipantes;
    private boolean isFood; //true==comida, false==reunion
    private int asistentes;

    public ProductMeetingFood(int id, String name, double price, LocalDate date, int maxParticipantes, boolean isFood) {
        super(id, name, null, price);
        try {
            this.date = date;
            if (maxParticipantes <= 100)
                if (maxParticipantes > 0)
                    this.maxParticipantes = maxParticipantes;
                else
                    throw new NegativeNumException("No se pueden añadir participantes negativos");
            else {
                throw new MaxAssistantOutOfBounds("Las reuniones y las comidas pueden tener como máximo 100 asistentes");
            }
            this.isFood = isFood;
            this.asistentes = 0;
        } catch (DateTimeParseException ex) {
            System.out.println("El formato de la fecha debe ser yyyy-MM-dd");
        }

    }

    public boolean setAsistentes(int asistentes) {
        if (asistentes > maxParticipantes) {
            System.out.println("No se pueden añadir mas de " + this.maxParticipantes + " participantes." +
                    " Intentalo de nuevo con un numero valido de integrantes");
            return false;
        } else {
            if (asistentes > 0)
                this.asistentes = asistentes;
            else{
                System.out.println("No se pueden añadir participantes negativos");
                return false;
            }
            return true;
        }
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
        if (asistentes == 0)
            return "{class:" + ((isFood) ? "Food" : "Meeting") +
                    ", id:" + id + ", name:'" + name + "', price:" + price * asistentes + ", date of Event:" + date +
                    ", max people allowed:" + maxParticipantes + "}";
        else
            return "{class:" + ((isFood) ? "Food" : "Meeting") +
                    ", id:" + id + ", name:'" + name + "', price:" + price * asistentes + ", date of Event:" + date +
                    ", max people allowed:" + maxParticipantes + ", actual people in event:" + asistentes + "}";
    }

    @Override
    public ProductMeetingFood clone() {
        return new ProductMeetingFood(id, name, price, date, maxParticipantes, isFood);
    }
}
