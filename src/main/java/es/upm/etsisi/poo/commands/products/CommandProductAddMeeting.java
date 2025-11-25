package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ProductFoodMeetingController;

import java.time.LocalDate;

public class CommandProductAddMeeting implements Command {

    private final int id;
    private final String name;
    private final double price;
    private final String date;
    private final int maxPeople;

    public CommandProductAddMeeting(Integer id, String name, double price, String date, int maxPeople) {
        if (id != null) {
            this.id = id;
        } else {
            this.id = ProductFoodMeetingController.generarId();
        }
        this.name = name;
        this.price = price;
        this.date = date;
        this.maxPeople = maxPeople;
    }

    @Override
    public boolean execute() {
        ProductFoodMeetingController.add(id, name, null, price, date, maxPeople, false);
        return true;
    }
}
