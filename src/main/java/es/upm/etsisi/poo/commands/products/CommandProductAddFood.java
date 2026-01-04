package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.product.ProductFoodMeetingController;

public class CommandProductAddFood implements Command {
    private final String id;
    private final String name;
    private final double price;
    private final String date;
    private final int maxPeople;

    public CommandProductAddFood(String id, String name, double price, String date, int maxPeople) {
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
        ProductFoodMeetingController.add(id, name, price, date, maxPeople, true);
        return true;
    }

}
