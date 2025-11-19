package es.upm.etsisi.poo.commands.tickets;

import es.upm.etsisi.poo.commands.Command;

public class CommandTicketAddProduct implements Command {

        private final String ticketId;
        private final String cashId;
        private final String productId;
        private final int amount;
        private final String[] pers; // --p<txt>

        public CommandTicketAddProduct(String ticketId, String cashId, String productId, int amount, String[] pers) {
            this.ticketId = ticketId;
            this.cashId = cashId;
            this.productId = productId;
            this.amount = amount;
            this.pers = pers;
        }

        @Override
        public boolean execute() {
            // TODO

            return true;
        }
}
