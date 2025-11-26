package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.BASES_DE_DATOS.ProductDB;
import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ProductController;
import es.upm.etsisi.poo.controler.TicketController;
import es.upm.etsisi.poo.models.Product;
import es.upm.etsisi.poo.models.ProductCustom;
import es.upm.etsisi.poo.models.ProductMeeting;

public class CommandTicketAddProduct implements Command {
    private final String ticketId;
    private final String cashId;
    private final int productId;
    private final int amount;
    private final String[] pers; // --p<txt>

    //TODO controller esta ,mal
    public CommandTicketAddProduct(String ticketId, String cashId, int productId, int amount, String[] pers) {
        this.ticketId = ticketId;
        this.cashId = cashId;
        this.productId = productId;
        this.amount = amount;
        this.pers = pers;
    }

    //TODO existe esta otacion que te sale en intellij como una lista de tareas
    @Override
    public boolean execute() {// hacer dos add, uno con personalizacion y otro sin
        Product product = ProductController.findId(this.productId);
        if (!(ProductController.findId(this.productId) instanceof ProductMeeting)) {
            if (pers != null) {
                TicketController.addProductPers(this.ticketId, product, this.amount, this.pers);// con pers
            } else {
                TicketController.addProduct(this.ticketId, product, this.amount);// sin pers
            }
        }else
             TicketController.addMeeting(this.ticketId, (ProductMeeting) product, this.amount);
        return true;

    }
}
