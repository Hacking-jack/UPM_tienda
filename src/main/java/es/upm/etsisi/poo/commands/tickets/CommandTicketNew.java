package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;

public class CommandTicketNew implements Command {
    private final String id; // null si no se da
    private final String cashId;
    private final String userId;

    public CommandTicketNew(String id, String cashId, String userId) {
        this.id = id;
        this.cashId = cashId;
        this.userId = userId;
    }

    @Override
    public void execute() {
        // TODO
    }
}
