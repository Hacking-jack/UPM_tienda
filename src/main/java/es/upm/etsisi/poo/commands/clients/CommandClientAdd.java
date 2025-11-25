package es.upm.etsisi.poo.commands.clients;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ClientController;

public class CommandClientAdd implements Command {

    private final String name;
    private final String dni;
    private final String email;
    private final String cashId;

    public CommandClientAdd(String name, String dni, String email, String cashId) {
        this.name = name;
        this.dni = dni;
        this.email = email;
        this.cashId = cashId;
    }

    @Override
    public boolean execute() {
        ClientController.add(name, dni, email, cashId);
        return true;
    }
}
