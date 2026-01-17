package es.upm.etsisi.poo.models.ticket.printBehaviour;

import es.upm.etsisi.poo.exceptions.ticket.*;
import es.upm.etsisi.poo.models.product.*;
import es.upm.etsisi.poo.models.ticket.States;
import es.upm.etsisi.poo.models.ticket.Ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class TicketProductPrint implements TicketPrintBehaviour<ProductBasic> {

    public Class<ProductBasic> getTipo(){
        return ProductBasic.class;
    }

    @Override
    public void printAndClose(Ticket<ProductBasic> ticket) {
        if (ticket.getEstado() == States.VACIO) {
            throw new NotSatisfiedMinimumRequirementsException();
        } else {
            if ((ticket.getEstado() != States.CERRADO) && !ticket.comprobarCaducidad()) {
                throw new ExpiredProductsException();
            }
            if (ticket.getEstado() != States.CERRADO) {
                ticket.setEstado(States.CERRADO);
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("-yy-MM-dd-HH:mm");
            ticket.setIdTicket(ticket.getIdTicket() + LocalDateTime.now().format(formatter));
            print(ticket);
        }
    }

    public void print(Ticket<ProductBasic> ticket) {
        System.out.println(getStringPrint(ticket));
    }

    @Override
    public String getStringPrint(Ticket<ProductBasic> ticket) {
        StringBuilder sb = new StringBuilder();

        ArrayList<ProductBasic> products = ticket.getProducts();
        products.sort(Comparator.comparing(Product::getName));
        double precioTotal = 0;
        int counterStationary = 0, counterClothes = 0, counterBook = 0, counterElectronic = 0;

        for (ProductBasic p : products) {
            precioTotal += p.getPrice();
            if (p.getCategories() != null) {
                switch (p.getCategories()) {
                    case STATIONERY -> counterStationary++;
                    case CLOTHES -> counterClothes++;
                    case BOOK -> counterBook++;
                    case ELECTRONICS -> counterElectronic++;
                }
            }
        }
            double descuentoTotal = 0;

            sb.append(String.format("Ticket : %s%n", ticket.getIdTicket()));
            for (ProductBasic p : products) {
                double discountAmount = 0.0;
                boolean discount = ticket.hasDiscount(counterStationary, counterClothes, counterBook, counterElectronic,
                        p.getCategories());
                if (discount) {
                    discountAmount = Categories.getDiscount(p.getCategories()) * p.getPrice();
                    descuentoTotal += discountAmount;
                }
                if (discountAmount == 0.0) {
                    sb.append("  ").append(p.toString()).append("\n");
                } else {
                    sb.append("  ").append(p.toString())
                            .append(String.format(" **discount -%.2f%n", discountAmount));
                }
            }
            double precioFinal = precioTotal - descuentoTotal;
            sb.append(String.format("  Total price: %.2f%n", precioTotal));
            sb.append(String.format("  Total discount: %.2f%n", descuentoTotal));
            sb.append(String.format("  Final Price: %.2f", precioFinal));

            return sb.toString();
        }
    }
