package es.upm.etsisi.poo.models.ticket.printBehaviour;

import es.upm.etsisi.poo.exceptions.ticket.ExpiredProductsException;
import es.upm.etsisi.poo.exceptions.ticket.NotSatisfiedMinimumRequirementsException;
import es.upm.etsisi.poo.models.product.Categories;
import es.upm.etsisi.poo.models.product.Product;
import es.upm.etsisi.poo.models.product.ProductBasic;
import es.upm.etsisi.poo.models.product.ProductService;
import es.upm.etsisi.poo.models.ticket.States;
import es.upm.etsisi.poo.models.ticket.Ticket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class TicketMixPrint implements TicketPrintBehaviour<Product>{
    @Override
    public void printAndClose(Ticket<Product> ticket) {
        boolean hayProducto = false;
        boolean hayServicio = false;

        for (Product p : ticket.getProducts()) {
            if (p instanceof ProductService) {
                hayServicio = true;
            } else {
                hayProducto = true;
            }
        }
        if (!hayProducto || !hayServicio) {
            throw new NotSatisfiedMinimumRequirementsException();
        }
        if((ticket.getEstado() != States.CERRADO) && !ticket.comprobarCaducidad()){
            throw new ExpiredProductsException();
        }
        if (ticket.getEstado() != States.CERRADO) {
            ticket.setEstado(States.CERRADO);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("-yy-MM-dd-HH:mm");
        ticket.setIdTicket(ticket.getIdTicket() + LocalDateTime.now().format(formatter));
        print(ticket);
    }

    public void print(Ticket<Product> ticket){
        System.out.println(getStringPrint(ticket));
    }

    @Override
    public String getStringPrint(Ticket<Product> ticket) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Product> products = ticket.getProducts();
        products.sort(
                Comparator
                        .comparing((Product p) -> !(p instanceof ProductService))
                        .thenComparing(p -> {
                            if (p instanceof ProductService) {
                                return p.getId();
                            }
                            return null;
                        }, Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(Product::getName)
        );

        int serviceCount = 0;

        for (Product p : products) {
            if (p instanceof ProductService) {
                serviceCount++;
            }
        }

        double descuentoServicios = serviceCount * 0.15;
        sb.append(String.format("Ticket : %s", ticket.getIdTicket()));
        if(serviceCount!=0){
            sb.append("\nServices Included:");
            for (Product p : products) {
                if (p instanceof ProductService) {
                    sb.append("\n  ").append(p.toString());
                }
            }
        }
        int i=0;
        if(serviceCount<products.toArray().length){
            sb.append("\nProducts Included:");
        }
        double precioTotal = 0;
        int counterStationary = 0, counterClothes = 0, counterBook = 0, counterElectronic = 0;

        for (Product p : products) {
            precioTotal += p.getPrice();
            if (p instanceof ProductBasic) {
                i++;
                ProductBasic pb = (ProductBasic) p;
                if (pb.getCategories() != null) {
                    switch (pb.getCategories()) {
                        case STATIONERY -> counterStationary++;
                        case CLOTHES -> counterClothes++;
                        case BOOK -> counterBook++;
                        case ELECTRONICS -> counterElectronic++;
                    }
                }
            }
        }
        double descuentoTotalCategories = 0;

        for (Product p : products) {
            double discountcategories = 0.0;
            if(!(p instanceof ProductService)){
                if (p instanceof ProductBasic) {
                    ProductBasic pb = (ProductBasic) p;
                    boolean discount = ticket.hasDiscount(counterStationary, counterClothes, counterBook, counterElectronic,
                            pb.getCategories());
                    if (discount) {
                        discountcategories = Categories.getDiscount(pb.getCategories()) * p.getPrice();
                        descuentoTotalCategories += discountcategories;
                    }
                }

                if (discountcategories == 0.0) {
                    sb.append("\n  ").append(p.toString());
                } else {
                    sb.append("\n  ").append(p.toString())
                            .append(String.format(" **discount -%.2f", discountcategories));
                }
            }
        }
        double precioFinal = Math.max(precioTotal - descuentoTotalCategories - (precioTotal*descuentoServicios), 0);
        if(i>=1){
            sb.append(String.format("%n  Total price: %.2f", precioTotal));
            sb.append(String.format("%n  Extra Discount from services:%.2f **discount -%.2f", (precioTotal*descuentoServicios), (precioTotal*descuentoServicios)));
            sb.append(String.format("%n  Total discount: %.2f", descuentoTotalCategories+(precioTotal*descuentoServicios)));
            sb.append(String.format("%n  Final Price: %.2f", precioFinal));
        }
        return sb.toString();
    }
}
