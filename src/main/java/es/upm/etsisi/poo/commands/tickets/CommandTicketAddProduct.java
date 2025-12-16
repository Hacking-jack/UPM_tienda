package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.dataBase.HumanDB;
import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ProductController;
import es.upm.etsisi.poo.controler.TicketController;
import es.upm.etsisi.poo.exceptions.NegativeNumException;
import es.upm.etsisi.poo.models.product.ProductBasic;
import es.upm.etsisi.poo.models.product.ProductBasicMeeting;

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
                if (!(productBasic instanceof ProductBasicMeeting)) {
                    if (pers != null) { //con pers
                        TicketController.addProductPers(this.ticketId, productBasic, this.amount, this.pers);
                    } else { //sin pers
                        TicketController.addProduct(this.ticketId, productBasic, this.amount);
                    }
                } else //foodMeeting
                    TicketController.addMeeting(this.ticketId, (ProductBasicMeeting) productBasic, this.amount);
            } else {
                System.out.println("Ese ticket no pertenece a ese cajero");
            }
        }else
            throw new NegativeNumException("No se pueden añadir productos negativos");
        return true;

    }
}
