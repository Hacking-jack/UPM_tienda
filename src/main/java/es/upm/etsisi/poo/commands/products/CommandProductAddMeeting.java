package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;

import java.time.LocalDate;

public class CommandProductAddMeeting implements Command {

    private final String id;
    private final String name;
    private final double price;
    private final LocalDate date;
    private final int maxPeople;

    public CommandProductAddMeeting(String id, String name, double price, LocalDate date, int maxPeople) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.maxPeople = maxPeople;
    }

    @Override
    public void execute() {
        // TODO
    }
}
