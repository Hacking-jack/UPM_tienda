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
    private final ProductFoodMeetingController productFoodMeetingController;

    public CommandProductAddMeeting(Integer id, String name, double price, String date, int maxPeople,
                                    ProductFoodMeetingController productFoodMeetingController) {
        if(id!=null) {
            this.id = id;
        }else{
            this.id=productFoodMeetingController.generarId();
        }
        this.name = name;
        this.price = price;
        this.date = date;
        this.maxPeople = maxPeople;
        this.productFoodMeetingController = productFoodMeetingController;
    }

    @Override
    public boolean execute() {
        productFoodMeetingController.add(id, name, null, price, date, maxPeople, false);
        return true;
    }
}
