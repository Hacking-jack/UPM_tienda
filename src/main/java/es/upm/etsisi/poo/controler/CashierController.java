package es.upm.etsisi.poo.controler;
import es.upm.etsisi.poo.BASES_DE_DATOS.HumanDB;
import es.upm.etsisi.poo.models.Cashier;
import es.upm.etsisi.poo.models.Human;
import es.upm.etsisi.poo.models.Ticket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class CashierController {


    public CashierController() {

    }


    public void add(String id, String nombre, String email) {
        if (id != null && searchId(id) != null) {
            System.out.println("Error. Ya existe un cajero con ese id.");
            return;
        }
        Cashier nuevo = new Cashier(id, nombre, email);
        HumanDB.addUser(nuevo);
        System.out.println(nuevo);
    }

    public void remove(String id) {
        Cashier c = searchId(id);
        if (c == null) {
            System.out.println("Error. Cajero no encontrado.");
            return;
        }
        System.out.println(c);
        HumanDB.removeHuman(c);
    }

    public void list() {
        for (Human human:HumanDB.list()) {
            if(human instanceof Cashier){
                System.out.println(human.toString());
            }
        }
    }



    public Cashier searchId(String id) {
        for (Human h:HumanDB.list()) {
            if(h instanceof Cashier && h.getId().equals(id)){
                return (Cashier) h;
            }
        }
        return null;
    }

    public void cashTickets(String cashId) {
        List<Ticket> ticketsDelCajero = historyController.searchTicketsByCashier(cashId);

        if (ticketsDelCajero.isEmpty()) {
            System.out.println("Tickets: \ncash tickets: ok");
            return;
        }

        System.out.println("Tickets: ");

        ticketsDelCajero.sort(Comparator.comparing(Ticket::getDate));

        for (int i = 0; i < ticketsDelCajero.size(); i++) {
            Ticket ticket = ticketsDelCajero.get(i);

            String status = ticket.getProducts().isEmpty() ? "EMPTY" : "CLOSE";

            System.out.printf("  %s->%s\n", ticket.getIdTicket(), status);
        }
        System.out.println("cash tickets: ok");
    }

    public String generarId(){
        return "UW" + (1_000_000 + new Random().nextInt(9_000_000));
    }
}

