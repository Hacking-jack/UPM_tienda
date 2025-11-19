package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ProductController;

public class CommandProductRemove implements Command {
    private final int id;
    private final ProductController productController;

    public CommandProductRemove(int id, ProductController productController) {
        this.id = id;
        this.productController=productController;
    }

    @Override
    public boolean execute() {
        productController.remove(id);
        return true;
    }
}
