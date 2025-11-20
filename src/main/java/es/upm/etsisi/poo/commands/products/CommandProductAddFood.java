package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ProductFoodMeetingController;
import es.upm.etsisi.poo.models.ProductFoodMeeting;

import java.time.LocalDate;

public class CommandProductAddFood implements Command {
    private final int id;
    private final String name;
    private final double price;
    private final String date;
    private final int maxPeople;
    private final ProductFoodMeetingController productFoodMeetingController;

    public CommandProductAddFood(int id, String name, double price, String date, int maxPeople,
                                 ProductFoodMeetingController productFoodMeetingController) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.maxPeople = maxPeople;
        this.productFoodMeetingController = productFoodMeetingController;
    }

    @Override
    public boolean execute() {
        productFoodMeetingController.add(id, name, null, price, date, maxPeople, true);
        return true;
    }

}
