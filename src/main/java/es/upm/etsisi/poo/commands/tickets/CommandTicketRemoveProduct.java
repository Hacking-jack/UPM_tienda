package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;

public class CommandTicketRemoveProduct implements Command {


    private final String ticketId;
    private final String cashId;
    private final String productId;

    public CommandTicketRemoveProduct(String ticketId, String cashId, String productId) {
        this.ticketId = ticketId;
        this.cashId = cashId;
        this.productId = productId;
    }

    @Override
    public void execute() {
        // TODO
    }
}
