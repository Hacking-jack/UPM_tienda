package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;

public class CommandTicketNew implements Command {
    private final String providedId; // null si no se da
    private final String cashId;
    private final String userId;

    public CommandTicketNew(String providedId, String cashId, String userId) {
        this.providedId = providedId;
        this.cashId = cashId;
        this.userId = userId;
    }

    @Override
    public void execute() {
        // TODO
    }
}
