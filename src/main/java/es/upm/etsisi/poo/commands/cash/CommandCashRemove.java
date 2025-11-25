package es.upm.etsisi.poo.commands.cash;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.CashierController;

import java.util.Calendar;

public class CommandCashRemove implements Command {
    private final String id;

    public CommandCashRemove(String id) {
        this.id = id;
    }

    @Override
    public boolean execute() {
        CashierController.remove(id);
        return true;
    }
}
