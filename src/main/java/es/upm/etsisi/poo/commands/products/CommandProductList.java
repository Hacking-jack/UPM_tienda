package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.product.ProductController;

public class CommandProductList implements Command {


    public CommandProductList() {
    }

    @Override
    public boolean execute() {
        ProductController.list();
        return true;
    }
}
