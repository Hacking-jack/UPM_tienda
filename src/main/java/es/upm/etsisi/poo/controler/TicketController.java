package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.models.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TicketController {

    private Ticket ticket;
    private int counter;
    private final HistorySalesController historyController;
    private final CashierController cashierController;
    private final ClientController clientController;
    private String currentClientDNI;


    public TicketController(HistorySalesController historyController, CashierController cashierController,
                            ClientController clientController) {
        this.historyController = historyController;
        this.cashierController = cashierController;
        this.clientController = clientController;
        this.newTicketState(null, null, null);
    }

    public void newTicketState(String id, String cashId, String clientDNI) {
        this.currentClientDNI = clientDNI;
        this.ticket = new Ticket();
        this.counter = 0;
    }

    public void add(Product product, int quantity) {
        if (counter < 100) {
            boolean stop = false;
            int addedCount = 0;
            for (int i = 0; i < quantity; i++) {
                if (counter < 100) {
                    ticket.getProducts().add(product);
                    counter++;
                    addedCount++;
                } else {
                    stop = true;
                    break;
                }
            }
            if (stop) System.out.println("No se pudieron añadir todos los productos, se han añadido " + addedCount +
                    " hasta llegar al limite de 100");
        } else {
            System.out.println("Ticket lleno.");
        }
    }

    public void remove(Product product) {
        while (ticket.getProducts().contains(product)) {
            ticket.getProducts().remove(product);
            counter--;
        }
    }

    public void print(String ticketId, String cashId) {

        List<Product> products = ticket.getProducts();

        if (products.isEmpty()) {
            System.out.println("Error. El ticket está vacío.");
            return;
        }

        List<Product> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Comparator.comparing(Product::getName));

        double precioTotal = 0;
        int counterStationary = 0, counterClothes = 0, counterBook = 0, counterElectronic = 0;
        for (Product p : products) {
            precioTotal += p.getPrice();
            if (p.getCategories() == Categories.STATIONERY) {
                counterStationary++;
            } else if (p.getCategories() == Categories.CLOTHES) {
                counterClothes++;
            } else if (p.getCategories() == Categories.BOOK) {
                counterBook++;
            } else if (p.getCategories() == Categories.ELECTRONICS) {
                counterElectronic++;
            }
        }

        double descuentoTotal = 0;

        // ⭐⭐ LÍNEAS DE DEFINICIÓN AÑADIDAS ⭐⭐
        LocalDateTime now = LocalDateTime.now();
        String finalTicketId = ticketId + "-" + now.format(DateTimeFormatter.ofPattern("dd-MM-yy-HH:mm"));

        System.out.printf("Ticket : %s%n", finalTicketId); // ⭐ Se usa finalTicketId aquí

        for (int i = 0; i < sortedProducts.size(); i++) {
            Product p = sortedProducts.get(i);
            boolean discount = hasDiscount(counterStationary, counterClothes, counterBook, counterElectronic,
                    p.getCategories());
            double discountAmount = 0.0;
            if (discount) {
                discountAmount = Categories.getDiscount(p.getCategories()) * p.getPrice();
                descuentoTotal += discountAmount;
            }
            if (discountAmount == 0.0) {
                System.out.println("  " + p.toString());
            } else {
                System.out.print("  " + p.toString());
                System.out.printf(" **discount -%.2f%n", discountAmount);
            }
        }

        double precioFinal = precioTotal - descuentoTotal;
        System.out.printf("  Total price: %.2f%n", precioTotal);
        System.out.printf("  Total discount: %.2f%n", descuentoTotal);
        System.out.printf("  Final Price: %.2f%n", precioFinal);

        Cashier cashier = cashierController.searchId(cashId);
        Client client = clientController.dniSearch(this.currentClientDNI);

        if (cashier == null) {
            System.out.println("Error fatal: Cajero no encontrado para guardar el ticket.");
            return;
        }

        Ticket completedTicket = new Ticket(finalTicketId, now, products, precioFinal, States.CERRADO);
        historyController.saveCompletedTicket(completedTicket, cashier, client);
        this.newTicketState(null, null, null);
    }

    public boolean hasDiscount(int counterStationary, int counterClothes, int counterBook, int counterElectronic,
                               Categories categories) {
        boolean correct = false;
        switch (categories) {
            case STATIONERY:
                if (counterStationary >= 2) {
                    correct = true;
                }
                break;
            case CLOTHES:
                if (counterClothes >= 2) {
                    correct = true;
                }
                break;
            case BOOK:
                if (counterBook >= 2) {
                    correct = true;
                }
                break;
            case ELECTRONICS:
                if (counterElectronic >= 2) {
                    correct = true;
                }
                break;
        }
        return correct;
    }
}