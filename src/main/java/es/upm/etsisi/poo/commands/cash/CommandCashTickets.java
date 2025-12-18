package es.upm.etsisi.poo.commands.cash;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.human.CashierController;

public class CommandCashTickets implements Command {
    private final String cashId;

    public CommandCashTickets(String cashId) {
        this.cashId = cashId;
    }

    @Override
    public boolean execute() {
        CashierController.cashTickets(cashId);
        return true;
    }
}
