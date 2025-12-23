package es.upm.etsisi.poo.models.product;

import es.upm.etsisi.poo.exceptions.product.AssistantOutOfBoundsException;
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
                    throw new NegativeNumException();
            else {
                throw new MaxAssistantOutOfBounds();
            }
            this.isFood = isFood;
            this.asistentes = 0;
        } catch (DateTimeParseException ex) {
            System.out.println("El formato de la fecha debe ser yyyy-MM-dd");
        }

    }

    public boolean setAsistentes(int asistentes) {
        if (asistentes > maxParticipantes) {
            throw new AssistantOutOfBoundsException(this.maxParticipantes);
        } else {
            if (asistentes > 0)
                this.asistentes = asistentes;
            else {
                throw new NegativeNumException();
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
