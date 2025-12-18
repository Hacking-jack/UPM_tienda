package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.human.CashierController;
import es.upm.etsisi.poo.controler.human.ClientController;
import es.upm.etsisi.poo.controler.ticket.TicketController;

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
        if (ClientController.existeId(clientId) && CashierController.existeId(cashId)) {
            this.id = TicketController.newTicket(id);
            ClientController.searchId(clientId).addTicket(id);
            CashierController.searchId(cashId).addTicket(id);
        } else {
            System.out.println("Alguno de los usuarios no existe");
        }
        return true;
    }
}
