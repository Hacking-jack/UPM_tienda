package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ProductController;

public class CommandProductRemove implements Command {
    private final int id;

    public CommandProductRemove(int id) {
        this.id = id;
    }

    @Override
    public boolean execute() {
        ProductController.remove(id);
        return true;
    }
}
