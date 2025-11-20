package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ProductController;
import es.upm.etsisi.poo.controler.TicketController;

public class CommandTicketAddProduct implements Command {
    private final String ticketId;
    private final String cashId;
    private final String productId;
    private final int amount;
    private final String[] pers; // --p<txt>
    private final TicketController ticketController;
    private final ProductController productController;


    public CommandTicketAddProduct(String ticketId, String cashId, String productId, int amount, String[] pers,
                                   TicketController ticketController, ProductController productController) {
        this.ticketId = ticketId;
        this.cashId = cashId;
        this.productId = productId;
        this.amount = amount;
        this.pers = pers;
        this.ticketController = ticketController;
        this.productController = productController;
    }

    @Override
    public boolean execute() {
        ticketController.add()//Lo dejo asi para que no se nos olvide hacerlo
        return true;
    }
}
