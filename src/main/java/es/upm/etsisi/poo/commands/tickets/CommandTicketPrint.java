package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.CashierController;
import es.upm.etsisi.poo.controler.TicketController;

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
            System.out.println("Ese ticket no pertenece al cajero indicado");
        }
        return true;
    }
}