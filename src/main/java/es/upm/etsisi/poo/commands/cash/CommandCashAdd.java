package es.upm.etsisi.poo.commands.cash;

import es.upm.etsisi.poo.commands.Command;

public class CommandCashAdd implements Command {

    private final String id;
    private final String name;
    private final String email;

    public CommandCashAdd(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public void execute() {
        // TODO
    }
}
