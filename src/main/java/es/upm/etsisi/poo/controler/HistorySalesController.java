package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class HistorySalesController {
/*
    private static class SaleRecord {
        private final Ticket ticket;
        private final Cashier cashier;
        private final User user;

        public SaleRecord(Ticket ticket, Cashier cashier, User user) {
            this.ticket = ticket;
            this.cashier = cashier;
            this.user = user;
        }

        public Cashier getCashier() {
            return cashier;
        }

        public Ticket getTicket() {
            return ticket;
        }
    }
    private final List<SaleRecord> salesHistory = new ArrayList<>();

    public void saveCompletedTicket(Ticket ticket, Cashier cashier, User user) {
        SaleRecord record = new SaleRecord(ticket, cashier, user);
        this.salesHistory.add(record);
    }
*/

    // Esta Clase como maximo se puede quedar esto.
    // Recomendacion: unificar en Human Controller ya que un Cashier es un hijo de Human.
    // Mirar uso de "instance of"
    // lo de arriba no lo uses, añade complejidad al proyecto y no tiene sentido
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
