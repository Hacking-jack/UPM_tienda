package es.upm.etsisi.poo.commands.clients;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ClientController;
import es.upm.etsisi.poo.models.Client;

public class CommandClientAdd implements Command {

    private final String name;
    private final String dni;
    private final String email;
    private final String cashId;
    private final ClientController clientController;

    public CommandClientAdd(String name, String dni, String email, String cashId, ClientController clientController) {
        this.name = name;
        this.dni = dni;
        this.email = email;
        this.cashId = cashId;
        this.clientController = clientController;
    }

    @Override
    public boolean execute() {
        clientController.add(name, dni, email, cashId);
        return true;
    }
}
