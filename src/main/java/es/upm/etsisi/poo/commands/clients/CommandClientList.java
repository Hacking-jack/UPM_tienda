package es.upm.etsisi.poo.commands.clients;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.human.ClientController;

public class CommandClientList implements Command {

    public CommandClientList() {

    }

    @Override
    public boolean execute() {
        ClientController.list();
        return true;
    }
}
