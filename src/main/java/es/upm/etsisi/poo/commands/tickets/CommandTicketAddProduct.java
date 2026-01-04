package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.dataBase.UserDB;
import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.product.ProductController;
import es.upm.etsisi.poo.controler.ticket.TicketController;
import es.upm.etsisi.poo.exceptions.product.NegativeNumException;
import es.upm.etsisi.poo.exceptions.ticket.CashierTicketMismatchException;
import es.upm.etsisi.poo.models.product.Product;
import es.upm.etsisi.poo.models.product.ProductBasic;
import es.upm.etsisi.poo.models.product.ProductMeetingFood;

public class CommandTicketAddProduct implements Command {
    private final String ticketId;
    private final String cashId;
    private final String productId;
    private final int amount;
    private final String[] pers; // --p<txt>


    public CommandTicketAddProduct(String ticketId, String cashId, String productId, int amount, String[] pers) {
        this.ticketId = ticketId;
        this.cashId = cashId;
        this.productId = productId;
        this.amount = amount;
        this.pers = pers;
    }

    @Override
    public boolean execute() {
        Product product = ProductController.findId(productId);
        if (amount <= 0) {
            throw new NegativeNumException();
        }
        if (!UserDB.findId(cashId).getTickets().contains(ticketId)) {
            throw new CashierTicketMismatchException();
        }
        if (product instanceof ProductMeetingFood) {
            TicketController.addMeeting(ticketId, (ProductMeetingFood) product, amount);
        }
        else if (product instanceof ProductBasic) {
            ProductBasic pb = (ProductBasic) product;
            if (pers != null) {
                TicketController.addProductPers(ticketId, pb, amount, pers);
            } else {
                TicketController.addProduct(ticketId, pb, amount);
            }
        }
        else {
            TicketController.addProduct(ticketId, product, amount);
        }

        return true;
    }
}
