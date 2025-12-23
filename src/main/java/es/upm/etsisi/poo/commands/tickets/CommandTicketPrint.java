package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.user.CashierController;
import es.upm.etsisi.poo.controler.ticket.TicketController;
import es.upm.etsisi.poo.exceptions.ticket.CashierTicketMismatchException;

public class CommandTicketPrint implements Command {
    private final String ticketId;
    private final String cashId;

    public CommandTicketPrint(String ticketId, String cashId) {
        this.ticketId = ticketId;
        this.cashId = cashId;
    }

    @Override
    public boolean execute() {
        if (CashierController.searchId(cashId).getTickets().contains(TicketController.findId(ticketId).getIdTicket())) {
            TicketController.print(ticketId);
        } else {
            throw new CashierTicketMismatchException();
        }
        return true;
    }
}