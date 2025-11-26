package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.BASES_DE_DATOS.TicketDB;
import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.CashierController;
import es.upm.etsisi.poo.controler.ClientController;
import es.upm.etsisi.poo.controler.TicketController;

import javax.swing.event.CaretListener;

public class CommandTicketNew implements Command {
    private String id; // null si no se da
    private final String cashId;
    private final String clientId;

    public CommandTicketNew(String id, String cashId, String clientId) {
        this.id = id;
        this.cashId = cashId;
        this.clientId = clientId;
    }

    @Override
    public boolean execute() {
        this.id = TicketController.newTicket(id);
        ClientController.searchId(clientId).addTicket(id);
        CashierController.searchId(cashId).addTicket(id);
        return true;
    }//TODO revisar
}
