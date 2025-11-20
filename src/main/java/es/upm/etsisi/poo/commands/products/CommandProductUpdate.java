package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ProductController;

public class CommandProductUpdate implements Command {

    private final int id;
    private final String field;
    private final String value;
    private final ProductController productController;

    public CommandProductUpdate(int id, String field, String value, ProductController productController) {
        this.id = id;
        this.field = field;
        this.value = value;
        this.productController = productController;
    }

    @Override
    public boolean execute() {
        productController.update(id, field, value);
        return true;
    }
}
