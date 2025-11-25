package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.TicketController;

public class CommandTicketList implements Command {


    //TODO hacer la lista de tickets
    @Override
    public boolean execute() {
        TicketController.list();
        return true;
    }
}
