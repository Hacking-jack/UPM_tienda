package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.TicketController;

import java.lang.classfile.TypeAnnotation;

public class CommandTicketList implements Command {
    private final TicketController ticketController;

    public CommandTicketList(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    @Override
    public boolean execute() {
        ticketController.list()//FALTA TODO
        return true;
    }
}
