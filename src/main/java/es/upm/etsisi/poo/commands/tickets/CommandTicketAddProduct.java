package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.BASES_DE_DATOS.ProductDB;
import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ProductController;
import es.upm.etsisi.poo.controler.TicketController;
import es.upm.etsisi.poo.models.Ticket;

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
    public boolean execute() {//hacer dos add, uno con personalizacion y otro sin
        Ticket t = TicketController.findId(ticketId);

        if(pers[0]==null) {// sin pers
            TicketController.addProductPers(t,ProductController.findId(productId), amount, pers);//Lo dejo asi para que no se nos olvide hacerlo
        }else{
            TicketController.addProduct(t,ProductDB.findId(productId), amount);//con pers
        }
        return true;
    }
}
