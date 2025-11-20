package es.upm.etsisi.poo.commands.clients;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ClientController;

public class CommandClientList implements Command {
    private final ClientController clientController;

    public CommandClientList(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public boolean execute() {
        clientController.list();
        return true;
    }
}
