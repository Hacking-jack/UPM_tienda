package es.upm.etsisi.poo.commands.clients;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ClientController;

public class CommandClientRemove implements Command {
    private final String dni;
    private final ClientController clientController;

    public CommandClientRemove(String dni, ClientController clientController) {
        this.dni = dni;
        this.clientController = clientController;
    }

    @Override
    public boolean execute() {
        clientController.remove(dni);
        return true;
    }
}
