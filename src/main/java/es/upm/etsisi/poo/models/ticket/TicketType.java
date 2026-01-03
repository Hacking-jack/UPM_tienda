package es.upm.etsisi.poo.models.ticket;

public enum TicketType {
    SERVICES,
    PRODUCTS,
    MIX;
    public String toString() {
        String string = null;
        switch (this) {
            case SERVICES:
                string = "SERVICES";
                break;
            case PRODUCTS:
                string = "PRODUCTS";
                break;
            case MIX:
                string = "MIX";
                break;
        }
        return string;
    }
}
