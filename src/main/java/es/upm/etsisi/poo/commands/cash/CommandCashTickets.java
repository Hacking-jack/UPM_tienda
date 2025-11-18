package es.upm.etsisi.poo.commands.cash;

import es.upm.etsisi.poo.commands.Command;

public class CommandCashTickets implements Command {
    private final String cashId;

    public CommandCashTickets(String cashId) {
        this.cashId = cashId;
    }

    @Override
    public void execute() {
        // TODO
    }
}
