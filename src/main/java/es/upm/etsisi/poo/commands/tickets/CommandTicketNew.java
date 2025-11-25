package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.BASES_DE_DATOS.TicketDB;
import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.CashierController;
import es.upm.etsisi.poo.controler.ClientController;
import es.upm.etsisi.poo.controler.TicketController;

import javax.swing.event.CaretListener;

public class CommandTicketNew implements Command {
    private final String id; // null si no se da
    private final String cashId;
    private final String clientId;

    public CommandTicketNew(String id, String cashId, String clientId) {
        if (id != null) {
            this.id = id;
        } else {
            this.id = TicketController.generarId();
        }
        this.cashId = cashId;
        this.clientId = clientId;
    }

    @Override
    public boolean execute() {
        ClientController.searchId(clientId).addTicket(id);
        CashierController.searchId(cashId).addTicket(id);
        TicketController.newTicket(id);
        return true;
    }//TODO revisar
}
