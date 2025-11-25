package es.upm.etsisi.poo.commands.cash;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.CashierController;

public class CommandCashAdd implements Command {

    private final String id;
    private final String name;
    private final String email;

    public CommandCashAdd(String id, String name, String email) {
        if (id != null) {
            this.id = id;
        } else {
            this.id = CashierController.generarId();
        }
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean execute() {
        CashierController.add(id, name, email);
        return true;
    }
}
