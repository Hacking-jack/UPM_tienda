package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.models.Cashier;
import es.upm.etsisi.poo.models.Client;
import es.upm.etsisi.poo.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class HistorySalesController {

    private static class SaleRecord {
        private final Ticket ticket;
        private final Cashier cashier;
        private final Client client;

        public SaleRecord(Ticket ticket, Cashier cashier, Client client) {
            this.ticket = ticket;
            this.cashier = cashier;
            this.client = client;
        }

        public Cashier getCashier() {
            return cashier;
        }

        public Ticket getTicket() {
            return ticket;
        }
    }
    private final List<SaleRecord> salesHistory = new ArrayList<>();

    public void saveCompletedTicket(Ticket ticket, Cashier cashier, Client client) {
        SaleRecord record = new SaleRecord(ticket, cashier, client);
        this.salesHistory.add(record);
    }

    public List<Ticket> searchTicketsByCashier(String cashId) {
        List<Ticket> ticketsDelCajero = new ArrayList<>();
        for (SaleRecord record : salesHistory) {
            if (record.getCashier() != null && record.getCashier().getId().equals(cashId)) {
                ticketsDelCajero.add(record.getTicket());
            }
        }
        return ticketsDelCajero;
    }
}
