package es.upm.etsisi.poo.commands.clients;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ClientController;

public class CommandClientList implements Command {

    public CommandClientList(ClientController clientController) {

    }

    @Override
    public boolean execute() {
        ClientController.list();
        return true;
    }
}
