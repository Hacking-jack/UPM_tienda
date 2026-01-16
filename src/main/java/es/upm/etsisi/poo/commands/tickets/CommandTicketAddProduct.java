package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.dataBase.UserDB;
import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.product.ProductController;
import es.upm.etsisi.poo.controler.ticket.TicketController;
import es.upm.etsisi.poo.exceptions.general.UnknownCommandException;
import es.upm.etsisi.poo.exceptions.product.NegativeNumException;
import es.upm.etsisi.poo.exceptions.ticket.CashierTicketMismatchException;
import es.upm.etsisi.poo.models.product.*;

public class CommandTicketAddProduct implements Command {
    private final String ticketId;
    private final String cashId;
    private final String productId;
    private final Integer amount;
    private final String[] pers; // --p<txt>


    public CommandTicketAddProduct(String ticketId, String cashId, String productId, Integer amount, String[] pers) {
        this.ticketId = ticketId;
        this.cashId = cashId;
        this.productId = productId;
        this.amount = amount;
        this.pers = pers;
    }

    @Override
    public boolean execute() {
        Product product = ProductController.findId(productId);
        if (amount!=null && amount <= 0) {
            throw new NegativeNumException();
        }
        if (!UserDB.findId(cashId).getTickets().contains(ticketId)) {
            throw new CashierTicketMismatchException();
        }
        if(amount!=null){
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
        }else{
            if(product instanceof ProductService) {
                TicketController.addService(ticketId, (ProductService) product);
            }else{
                throw new UnknownCommandException();
            }
        }

        return true;
    }
}
