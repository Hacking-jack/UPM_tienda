package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class HistorySalesController {

    private ArrayList<Ticket> completedTickets = new ArrayList<>();

    public void saveCompletedTicket(Ticket ticket) {
        this.completedTickets.add(ticket);
    }

    public List<Ticket> searchTicketsByCashier(String cashId) {
        List<Ticket> ticketsDelCajero = new ArrayList<>();

        for (Ticket ticket : completedTickets) {
            if (ticket.getCashier() != null && ticket.getCashier().getId().equals(cashId)) {
                ticketsDelCajero.add(ticket);
            }
        }
        return ticketsDelCajero;
    }
}
