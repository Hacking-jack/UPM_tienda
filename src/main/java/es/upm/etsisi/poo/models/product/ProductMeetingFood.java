package es.upm.etsisi.poo.models.product;

import es.upm.etsisi.poo.exceptions.product.AssistantOutOfBoundsException;
import es.upm.etsisi.poo.exceptions.product.MaxAssistantOutOfBounds;
import es.upm.etsisi.poo.exceptions.product.NegativeNumException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ProductMeetingFood extends ProductBasic {

    private LocalDate date;
    private int maxAssistants;
    private boolean isFood; //true==comida, false==reunion
    private int assistants;

    public ProductMeetingFood(String id, String name, double price, LocalDate date, int maxParticipantes, boolean isFood) {
        super(id, name, null, price);
        try {
            this.date = date;
            if (maxParticipantes <= 100) {
                if (maxParticipantes > 0) {
                    maxAssistants = maxParticipantes;
                } else {
                    throw new NegativeNumException();
                }
            } else {
                throw new MaxAssistantOutOfBounds();
            }
            this.isFood = isFood;
            assistants = 0;
        } catch (DateTimeParseException ex) {
            System.out.println("El formato de la fecha debe ser yyyy-MM-dd");
        }

    }

    public boolean setAsistentes(int asistentes) {
        if (asistentes > maxAssistants) {
            throw new AssistantOutOfBoundsException(maxAssistants);
        } else {
            if (asistentes > 0) {
                assistants = asistentes;
            } else {
                throw new NegativeNumException();
            }
            return true;
        }
    }

    public int getAssistants() {
        return assistants;
    }

    @Override
    public double getPrice() {
        return price * assistants;
    }

    @Override
    public String toString() {
        if (assistants == 0) {
            return "{class:" + ((isFood) ? "Food" : "Meeting") +
                    ", id:" + id + ", name:'" + name + "', price:" + price * assistants + ", date of Event:" + date +
                    ", max people allowed:" + maxAssistants + "}";
        } else {
            return "{class:" + ((isFood) ? "Food" : "Meeting") +
                    ", id:" + id + ", name:'" + name + "', price:" + price * assistants + ", date of Event:" + date +
                    ", max people allowed:" + maxAssistants + ", actual people in event:" + assistants + "}";
        }
    }
    @Override
    public ProductMeetingFood clone() {
        return (ProductMeetingFood) super.clone();
    }
}
