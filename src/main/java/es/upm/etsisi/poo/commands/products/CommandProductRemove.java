package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.product.ProductController;

public class CommandProductRemove implements Command {
    private final String id;

    public CommandProductRemove(String id) {
        this.id = id;
    }

    @Override
    public boolean execute() {
        ProductController.remove(id);
        return true;
    }
}
