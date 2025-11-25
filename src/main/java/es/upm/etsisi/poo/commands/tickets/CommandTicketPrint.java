package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.BASES_DE_DATOS.HumanDB;
import es.upm.etsisi.poo.BASES_DE_DATOS.TicketDB;
import es.upm.etsisi.poo.commands.Command;
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
        if (HumanDB.findId(cashId).getTickets().contains(TicketDB.findId(ticketId))) {
            TicketController.print(ticketId);
        } else {
            System.out.println("Ese ticket no pertenece al cajero indicado");
        }
        return true;
    }
}
//TODO revisar esto