package es.upm.etsisi.poo.commands.clients;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ClientController;

public class CommandClientRemove implements Command {
    private final String dni;

    public CommandClientRemove(String dni) {
        this.dni = dni;
    }

    @Override
    public boolean execute() {
        ClientController.remove(dni);
        return true;
    }
}
