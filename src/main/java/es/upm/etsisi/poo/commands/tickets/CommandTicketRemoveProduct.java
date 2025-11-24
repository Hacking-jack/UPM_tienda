package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.BASES_DE_DATOS.HumanDB;
import es.upm.etsisi.poo.BASES_DE_DATOS.ProductDB;
import es.upm.etsisi.poo.BASES_DE_DATOS.TicketDB;
import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ProductController;
import es.upm.etsisi.poo.controler.TicketController;

public class CommandTicketRemoveProduct implements Command {


    private final String ticketId;
    private final String cashId;
    private final int productId;
    private final TicketController ticketController;
    private final ProductController productController;

    public CommandTicketRemoveProduct(String ticketId, String cashId, int productId,
                                      TicketController ticketController, ProductController productController) {
        this.ticketId = ticketId;
        this.cashId = cashId;
        this.productId = productId;
        this.ticketController = ticketController;
        this.productController = productController;
    }

    @Override
    public boolean execute() {
        if(HumanDB.findId(cashId).getTickets().contains(TicketDB.findId(ticketId))) {
            ticketController.remove(ProductDB.findId(productId));
        }else{
            System.out.println("Ese ticket no pertenece al cajero indicado");
        }
        return true;
    }
}
