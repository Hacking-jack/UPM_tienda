package es.upm.etsisi.poo.commands.cash;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.CashierController;

public class CommandCashAdd implements Command {

    private final String id;
    private final String name;
    private final String email;
    private final CashierController cashierController;

    public CommandCashAdd(String id, String name, String email, CashierController cashierController) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cashierController = cashierController;
    }

    @Override
    public boolean execute() {
        cashierController.add(id, name, email);
        return true;
    }
}
