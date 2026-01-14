package es.upm.etsisi.poo.models.ticket;

import es.upm.etsisi.poo.exceptions.product.NotEnoughTimeException;
import es.upm.etsisi.poo.exceptions.ticket.ExpiredProductsException;
import es.upm.etsisi.poo.exceptions.ticket.NotSatisfiedMinimunRequirementsException;
import es.upm.etsisi.poo.exceptions.ticket.TicketClosedException;
import es.upm.etsisi.poo.exceptions.ticket.TicketTypeMismatchException;
import es.upm.etsisi.poo.models.product.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class TicketProduct extends Ticket{

    public TicketProduct(String id){
        super(id);
    }
    public TicketProduct(){
        super();
    }

    public void addService(ProductService productService){
        throw new TicketTypeMismatchException();
    }

    public boolean addProduct(Product product) {
        if(estado==States.CERRADO){
            throw new TicketClosedException();
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

    public void printAndClose() {
        if(estado==States.VACIO) {
            throw new NotSatisfiedMinimunRequirementsException();
        }else {
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
    }

    public void print() {
        System.out.println(getStringPrint());
    }

    public String getStringPrint(){
        StringBuilder sb = new StringBuilder();

        products.sort(Comparator.comparing(Product::getName));
        double precioTotal = 0;
        int counterStationary = 0, counterClothes = 0, counterBook = 0, counterElectronic = 0;

        for (Product p : products) {
            precioTotal += p.getPrice();
            if (p instanceof ProductBasic) {
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
        double descuentoTotal = 0;

        sb.append(String.format("Ticket : %s%n", idTicket));
        for (Product p : products) {
            double discountAmount = 0.0;
            if (p instanceof ProductBasic) {
                ProductBasic pb = (ProductBasic) p;
                boolean discount = hasDiscount(counterStationary, counterClothes, counterBook, counterElectronic,
                        pb.getCategories());
                if (discount) {
                    discountAmount = Categories.getDiscount(pb.getCategories()) * p.getPrice();
                    descuentoTotal += discountAmount;
                }
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
