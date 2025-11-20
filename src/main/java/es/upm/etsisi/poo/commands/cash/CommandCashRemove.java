package es.upm.etsisi.poo.commands.cash;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.CashierController;

import java.util.Calendar;

public class CommandCashRemove implements Command {
    private final String id;
    private final CashierController cashierController;

    public CommandCashRemove(String id, CashierController cashierController) {
        this.id = id;
        this.cashierController = cashierController;
    }

    @Override
    public boolean execute() {
        cashierController.remove(id);
        return true;
    }
}
