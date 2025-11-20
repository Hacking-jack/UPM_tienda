package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ProductController;
import es.upm.etsisi.poo.controler.ProductCustomController;
import es.upm.etsisi.poo.controler.ProductFoodMeetingController;

public class CommandProductList implements Command {
    private final ProductController productController;
    private final ProductCustomController productCustomController;
    private final ProductFoodMeetingController productFoodMeetingController;

    public CommandProductList(ProductController productController, ProductCustomController productCustomController,
                              ProductFoodMeetingController productFoodMeetingController) {
        this.productController = productController;
        this.productCustomController = productCustomController;
        this.productFoodMeetingController = productFoodMeetingController;
    }

    @Override
    public boolean execute() {
        productController.list();
        return true;
    }
}
