package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.dataBase.HumanDB;
import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ProductController;
import es.upm.etsisi.poo.controler.TicketController;
import es.upm.etsisi.poo.models.Product;
import es.upm.etsisi.poo.models.ProductMeeting;

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
        Product product = ProductController.findId(this.productId);
        if (HumanDB.findId(cashId).getTickets().contains(ticketId)) {
            if (!(product instanceof ProductMeeting)) {
                if (pers != null) { //con pers
                    TicketController.addProductPers(this.ticketId, product, this.amount, this.pers);
                } else { //sin pers
                    TicketController.addProduct(this.ticketId, product, this.amount);
                }
            } else //foodMeeting
                TicketController.addMeeting(this.ticketId, (ProductMeeting) product, this.amount);
        } else {
            System.out.println("Ese ticket no pertenece a ese cajero");
        }
        return true;

    }
}
