package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;

public class CommandProductRemove implements Command {
    private final String id;

    public CommandProductRemove(String id) {
        this.id = id;
    }

    @Override
    public void execute() {
        // TODO
    }
}
