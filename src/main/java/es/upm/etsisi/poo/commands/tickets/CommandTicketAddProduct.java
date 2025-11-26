package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.BASES_DE_DATOS.ProductDB;
import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ProductController;
import es.upm.etsisi.poo.controler.TicketController;

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
        if (pers == null) {// sin pers
            TicketController.addProductPers(this.ticketId, ProductController.findId(this.productId), this.amount, this.pers);//Lo dejo asi para que no se nos olvide hacerlo
        } else {
            TicketController.addProduct(this.ticketId, ProductDB.findId(this.productId), this.amount);// con pers
        }
        return true;
    }
}
