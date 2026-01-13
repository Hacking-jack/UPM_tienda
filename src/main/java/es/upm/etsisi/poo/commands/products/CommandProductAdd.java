package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.product.ProductController;
import es.upm.etsisi.poo.controler.product.ProductCustomController;
import es.upm.etsisi.poo.exceptions.product.IdUnformattedException;

public class CommandProductAdd implements Command {
    private final String id; // null si no se da
    private final String name;
    private final String category;
    private final double price;
    private final Integer maxPers; // null si no es personalizado

    public CommandProductAdd(String id, String name, String category, double price, Integer maxPers) {
        if (id != null) {
            this.id = id;
        } else {
            this.id = ProductController.generarId();
        }
        try {
            Integer.parseInt(this.id);
        }catch(NumberFormatException ex){
            throw new IdUnformattedException();
        }
        this.name = name;
        this.category = category.toUpperCase();
        this.price = price;
        this.maxPers = maxPers;
    }

    @Override
    public boolean execute() {
        if (maxPers == null) {
            ProductController.add(id, name, category, price);
        } else {
            ProductCustomController.add(id, name, category, price, maxPers);
        }
        return true;
    }
}
