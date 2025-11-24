package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.CashierController;
import es.upm.etsisi.poo.controler.ClientController;
import es.upm.etsisi.poo.controler.TicketController;

import javax.swing.event.CaretListener;

public class CommandTicketNew implements Command {
    private final String id; // null si no se da
    private final String cashId;
    private final String clientId;
    private final TicketController ticketController;
    private final CashierController cashierController;
    private final ClientController clientController;

    public CommandTicketNew(String id, String cashId, String clientId, TicketController ticketController,
                            ClientController clientController, CashierController cashierController) {
        if(id!=null) {
            this.id = id;
        }else{
            this.id= ticketController.generarId();
        }
        this.cashId = cashId;
        this.clientId = clientId;
        this.ticketController = ticketController;
        this.cashierController=cashierController;
        this.clientController=clientController;
    }

    @Override
    public boolean execute() {
        clientController.searchId(clientId).addTicket(id);
        cashierController.searchId(cashId).addTicket(id);
        ticketController.newTicket(id);
        return true;
    }
}
