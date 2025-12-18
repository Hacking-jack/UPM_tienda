package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.product.ProductController;

public class CommandProductUpdate implements Command {

    private final int id;
    private final String field;
    private final String value;

    public CommandProductUpdate(int id, String field, String value) {
        this.id = id;
        this.field = field;
        this.value = value;
    }

    @Override
    public boolean execute() {
        ProductController.update(id, field, value);
        return true;
    }
}
