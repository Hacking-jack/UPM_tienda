package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.user.CashierController;
import es.upm.etsisi.poo.controler.user.ClientController;
import es.upm.etsisi.poo.controler.ticket.TicketController;
import es.upm.etsisi.poo.exceptions.ticket.TicketTypeMismatchException;
import es.upm.etsisi.poo.exceptions.user.UserNotFoundException;

public class CommandTicketNew implements Command {
    private String id; // null si no se da
    private final String cashId;
    private final String clientId;
    private final Character tipoDeTicket;

    public CommandTicketNew(String id, String cashId, String clientId, Character tipoDeTicket) {
        this.id = id;
        this.cashId = cashId;
        this.clientId = clientId;
        this.tipoDeTicket=tipoDeTicket;
    }

    @Override
    public boolean execute() {
        if (ClientController.existeId(clientId)) {
            if (CashierController.existeId(cashId)) {
                if(ClientController.isClient(clientId) && ((tipoDeTicket == null) || (tipoDeTicket == 'p'))) {
                    id = TicketController.newTicketBasic(id);
                }else{
                    if(ClientController.isBusiness(clientId) && (tipoDeTicket == 's')){
                        id = TicketController.newTicketService(id);
                    }else {
                        if (ClientController.isBusiness(clientId) && (tipoDeTicket == 'c')) {
                            id = TicketController.newTicketMix(id);
                        }else{
                            throw new TicketTypeMismatchException();
                        }
                    }
                }
                ClientController.searchId(clientId).addTicket(id);
                CashierController.searchId(cashId).addTicket(id);
            } else {
                throw new UserNotFoundException(cashId);
            }
        } else {
            throw new UserNotFoundException(clientId);
        }
        return true;
    }
}
