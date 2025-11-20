package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ProductController;
import es.upm.etsisi.poo.controler.ProductCustomController;

public class CommandProductAdd implements Command {
    private final int id; // null si no se da
    private final String name;
    private final String category;
    private final double price;
    private final Integer maxPers; // null si no es personalizado
    private final ProductController productController;
    private final ProductCustomController productCustomController;

    public CommandProductAdd(int id, String name, String category, double price, Integer maxPers,
                             ProductController productController, ProductCustomController productCustomController) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.maxPers = maxPers;
        this.productController = productController;
        this.productCustomController = productCustomController;
    }

    @Override
    public boolean execute() {
        if (maxPers == null) {
            productController.add(id, name, category, price);
        } else {
            productCustomController.add(id, name, category, price, maxPers);
        }
        return true;
    }
}
