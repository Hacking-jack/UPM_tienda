package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.TicketController;

public class CommandTicketNew implements Command {
    private final String id; // null si no se da
    private final String cashId;
    private final String clientId;
    private final TicketController ticketController;

    public CommandTicketNew(String id, String cashId, String clientId, TicketController ticketController) {
        this.id = id;
        this.cashId = cashId;
        this.clientId = clientId;
        this.ticketController = ticketController;
    }

    @Override
    public boolean execute() {
        ticketController.newTicketState(id, cashId, clientId);
        return true;
    }
}
