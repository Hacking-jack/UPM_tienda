package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.TicketController;

public class CommandTicketPrint implements Command {
    private final String ticketId;
    private final String cashId;
    private final TicketController ticketController;

    public CommandTicketPrint(String ticketId, String cashId, TicketController ticketController) {
        this.ticketId = ticketId;
        this.cashId = cashId;
        this.ticketController = ticketController;
    }

    @Override
    public boolean execute() {
        ticketController.print(ticketId, cashId);
        return true;
    }
}
