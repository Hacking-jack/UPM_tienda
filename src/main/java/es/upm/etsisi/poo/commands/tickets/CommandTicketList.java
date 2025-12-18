package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.ticket.TicketController;

public class CommandTicketList implements Command {


    @Override
    public boolean execute() {
        TicketController.list();
        return true;
    }
}
