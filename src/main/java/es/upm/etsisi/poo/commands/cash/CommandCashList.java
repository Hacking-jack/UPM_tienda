package es.upm.etsisi.poo.commands.cash;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.human.CashierController;

public class CommandCashList implements Command {


    @Override
    public boolean execute() {
        CashierController.list();
        return true;
    }
}
