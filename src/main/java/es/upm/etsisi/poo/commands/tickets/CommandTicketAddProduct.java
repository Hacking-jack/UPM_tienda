package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.dataBase.HumanDB;
import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.product.ProductController;
import es.upm.etsisi.poo.controler.ticket.TicketController;
import es.upm.etsisi.poo.exceptions.product.NegativeNumException;
import es.upm.etsisi.poo.exceptions.ticket.CashierTicketMismatchException;
import es.upm.etsisi.poo.models.product.ProductBasic;
import es.upm.etsisi.poo.models.product.ProductMeetingFood;

public class CommandTicketAddProduct implements Command {
    private final String ticketId;
    private final String cashId;
    private final int productId;
    private final int amount;
    private final String[] pers; // --p<txt>


    public CommandTicketAddProduct(String ticketId, String cashId, int productId, int amount, String[] pers) {
        this.ticketId = ticketId;
        this.cashId = cashId;
        this.productId = productId;
        this.amount = amount;
        this.pers = pers;
    }

    @Override
    public boolean execute() {
        ProductBasic productBasic = ProductController.findId(this.productId);
        if (amount > 0) {
            if (HumanDB.findId(cashId).getTickets().contains(ticketId)) {
                if (!(productBasic instanceof ProductMeetingFood)) {
                    if (pers != null) { //con pers
                        TicketController.addProductPers(this.ticketId, productBasic, this.amount, this.pers);
                    } else { //sin pers
                        TicketController.addProduct(this.ticketId, productBasic, this.amount);
                    }
                } else //foodMeeting
                    TicketController.addMeeting(this.ticketId, (ProductMeetingFood) productBasic, this.amount);
            } else {
                throw new CashierTicketMismatchException();
            }
        } else
            throw new NegativeNumException();
        return true;

    }
}
