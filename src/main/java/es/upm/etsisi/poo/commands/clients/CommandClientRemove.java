package es.upm.etsisi.poo.commands.clients;

import es.upm.etsisi.poo.commands.Command;

public class CommandClientRemove implements Command {
    private final String dni;

    public CommandClientRemove(String dni) {
        this.dni = dni;
    }

    @Override
    public void execute() {
        // TODO
    }
}
