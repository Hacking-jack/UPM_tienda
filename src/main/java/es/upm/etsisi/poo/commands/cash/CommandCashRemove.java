package es.upm.etsisi.poo.commands.cash;

import es.upm.etsisi.poo.commands.Command;

public class CommandCashRemove implements Command {
    private final String id;

    public CommandCashRemove(String id) {
        this.id = id;
    }

    @Override
    public void execute() {
        // TODO
    }
}
