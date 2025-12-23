package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.dataBase.HumanDB;
import es.upm.etsisi.poo.dataBase.ProductDB;
import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.product.ProductController;
import es.upm.etsisi.poo.controler.ticket.TicketController;
import es.upm.etsisi.poo.exceptions.product.ProductNotFoundException;
import es.upm.etsisi.poo.exceptions.ticket.CashierTicketMismatchException;

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
            if (ProductDB.existeId(productId)) {
                TicketController.remove(TicketController.findId(ticketId), ProductController.findId(productId));
                TicketController.findId(ticketId).print();
            } else {
                throw new ProductNotFoundException(productId);
            }
        } else {
            throw new CashierTicketMismatchException();
        }
        return true;
    }
}
