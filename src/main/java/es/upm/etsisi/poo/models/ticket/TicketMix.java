package es.upm.etsisi.poo.models.ticket;

import es.upm.etsisi.poo.exceptions.product.NotEnoughTimeException;
import es.upm.etsisi.poo.exceptions.ticket.ExpiredProductsException;
import es.upm.etsisi.poo.exceptions.ticket.NotSatisfiedMinimumRequirementsException;
import es.upm.etsisi.poo.exceptions.ticket.ServiceAlreadyInTicketException;
import es.upm.etsisi.poo.exceptions.ticket.TicketClosedException;
import es.upm.etsisi.poo.models.product.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class TicketMix extends Ticket{
    public TicketMix(){
        super();
    }
    public TicketMix(String id){
        super(id);
    }

    public boolean addProduct(Product product) {
        if (products.isEmpty()) {
            estado = States.ACTIVO;
        }
        if (estado==States.VACIO) {
            estado = States.ACTIVO;
        }
        return (products.size() < 100) && products.add(product);
    }

    public void addMeeting(ProductMeetingFood productMeeting) {
        if(estado==States.CERRADO){
            throw new TicketClosedException();
        }
        int asistentes;
        ProductMeetingFood productMeetingFood;
        for (Product product : products) {
            if (product.getId().equals(productMeeting.getId())) {
                productMeetingFood = (ProductMeetingFood) product;
                asistentes = productMeetingFood.getAssistants();
                productMeeting.setAsistentes(productMeeting.getAssistants() + asistentes);
                removeProduct(product);
                break;
            }
        }
        if(!productMeeting.isExpired()) {
            if(estado==States.VACIO){
                estado=States.ACTIVO;
            }
            addProduct(productMeeting);
        }else{
            throw new NotEnoughTimeException();
        }
    }

    public void addService(ProductService productService){
        if(estado==States.CERRADO){
            throw new TicketClosedException();
        }
        if(products.contains(productService)){
            throw new ServiceAlreadyInTicketException();
        }
        if (products.isEmpty()) {
            estado = States.ACTIVO;
        }
        if(!productService.isExpired() && (products.size() < 100)){
            products.add(productService);
        }
    }

    public void printAndClose() {
        boolean hayProducto = false;
        boolean hayServicio = false;

        for (Product p : products) {
            if (p instanceof ProductService) {
                hayServicio = true;
            } else {
                hayProducto = true;
            }
        }
        if (!hayProducto || !hayServicio) {
            throw new NotSatisfiedMinimumRequirementsException();
        }
        if((estado != States.CERRADO) && !comprobarCaducidad()){
            throw new ExpiredProductsException();
        }
        if (estado != States.CERRADO) {
            estado = States.CERRADO;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("-yy-MM-dd-HH:mm");
        idTicket += LocalDateTime.now().format(formatter);
        print();
    }

    public void print() {
        System.out.println(getStringPrint());
    }

    public String getStringPrint(){
        StringBuilder sb = new StringBuilder();
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
        double totalProductos = 0.0;

        for (Product p : products) {
            if (p instanceof ProductService) {
                serviceCount++;
            }
        }

        double descuentoServicios = serviceCount * 0.15;
        sb.append(String.format("Ticket : %s", idTicket));
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
        double descuentoTotalServicios=0;
        double descuentoTotalCategories = 0;

        for (Product p : products) {
            double discountcategories = 0.0;
            if(!(p instanceof ProductService)){
                if (p instanceof ProductBasic) {
                    ProductBasic pb = (ProductBasic) p;
                    boolean discount = hasDiscount(counterStationary, counterClothes, counterBook, counterElectronic,
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
