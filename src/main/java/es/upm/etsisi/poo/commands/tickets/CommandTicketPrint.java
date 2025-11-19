package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;

public class CommandTicketPrint implements Command {
    private final String ticketId;
    private final String cashId;

    public CommandTicketPrint(String ticketId, String cashId) {
        this.ticketId = ticketId;
        this.cashId = cashId;
    }

    @Override
    public boolean execute() {
        // TODO

        return true;
    }
}
