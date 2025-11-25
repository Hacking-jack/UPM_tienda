package es.upm.etsisi.poo.commands.general;

import es.upm.etsisi.poo.commands.Command;

public class CommandEcho implements Command {
    private final String text;

    public CommandEcho(String text) {
        this.text = text;
    }

    @Override
    public boolean execute() {
        System.out.println(text+" \n");
        return true;
    }
}
