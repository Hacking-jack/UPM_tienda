package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;

public class CommandProductUpdate implements Command {

    private final String id;
    private final String field;
    private final String value;

    public CommandProductUpdate(String id, String field, String value) {
        this.id = id;
        this.field = field;
        this.value = value;
    }

    @Override
    public boolean execute() {
        // TODO

        return true;
    }
}
