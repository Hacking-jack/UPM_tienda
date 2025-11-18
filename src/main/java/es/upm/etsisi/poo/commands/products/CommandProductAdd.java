package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;

public class CommandProductAdd implements Command {
    private final String id; // null si no se da
    private final String name;
    private final String category;
    private final double price;
    private final Integer maxPers; // null si no es personalizado

    public CommandProductAdd(String id, String name, String category, double price, Integer maxPers) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.maxPers = maxPers;
    }

    @Override
    public void execute() {
        // TODO
    }
}
