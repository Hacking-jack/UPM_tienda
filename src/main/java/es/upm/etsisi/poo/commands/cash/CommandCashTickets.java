package es.upm.etsisi.poo.commands.cash;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.CashierController;

import java.util.Calendar;

public class CommandCashTickets implements Command {
    private final String cashId;
    private final CashierController cashierController;

    public CommandCashTickets(String cashId, CashierController cashierController) {
        this.cashId = cashId;
        this.cashierController = cashierController;
    }

    @Override
    public boolean execute() {
        cashierController.cashTickets(cashId);
        return true;
    }
}
