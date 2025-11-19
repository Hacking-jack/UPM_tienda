package es.upm.etsisi.poo.commands.cash;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.CashierController;

public class CommandCashList implements Command {
    private final CashierController cashierController;

    public CommandCashList(CashierController cashierController) {
        this.cashierController=cashierController;
    }

    @Override
    public boolean execute() {
        cashierController.list();
        return true;
    }
}
