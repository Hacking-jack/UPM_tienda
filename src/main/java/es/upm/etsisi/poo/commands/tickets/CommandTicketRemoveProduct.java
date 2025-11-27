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

    public CommandTicketRemoveProduct(String ticketId, String cashId, int productId) {
        this.ticketId = ticketId;
        this.cashId = cashId;
        this.productId = productId;
    }

    @Override
    public boolean execute() {//TODO chapuza
        if (HumanDB.findId(cashId).getTickets().contains(TicketController.findId(ticketId).getIdTicket())) {
            if(ProductDB.existeId(productId)) {
                TicketController.remove(TicketController.findId(ticketId), ProductController.findId(productId));
                TicketController.findId(ticketId).print();
            }else{
                System.out.println("No existe un producto con el id:"+productId);
            }
        } else {
            System.out.println("Ese ticket no pertenece al cajero indicado");
        }
        return true;
    }
}
