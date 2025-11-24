package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.TicketController;

public class CommandTicketList implements Command {
    private final TicketController ticketController;

    public CommandTicketList(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    //TODO hacer la lista de tickets
    @Override
    public boolean execute() {
        ticketController.list();
        return true;
    }
}
