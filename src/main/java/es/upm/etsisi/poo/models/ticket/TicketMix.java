package es.upm.etsisi.poo.models.ticket;

import es.upm.etsisi.poo.exceptions.product.NotEnoughTimeException;
import es.upm.etsisi.poo.exceptions.ticket.ExpiredProductsException;
import es.upm.etsisi.poo.exceptions.ticket.NotSatisfiedMinimunRequirementsException;
import es.upm.etsisi.poo.exceptions.ticket.ServiceAlreadyInTicketException;
import es.upm.etsisi.poo.exceptions.ticket.TicketClosedException;
import es.upm.etsisi.poo.models.product.Product;
import es.upm.etsisi.poo.models.product.ProductBasic;
import es.upm.etsisi.poo.models.product.ProductMeetingFood;
import es.upm.etsisi.poo.models.product.ProductService;

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
            throw new NotSatisfiedMinimunRequirementsException();
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
        for (Product p : products) {
            if (p instanceof ProductBasic) {
                i++;
                sb.append("\n  ").append(p.toString()).append("\n");

                double precio = p.getPrice();
                double descuento = precio * descuentoServicios;
                double precioFinal = Math.max(precio - descuento, 0);

                totalProductos += precioFinal;

                //sb.append(String.format("  %s %.2f", p.toString(), precioFinal)); TODO no se qeu hacer con esto

                if (descuento > 0) {
                    sb.append(String.format(" **discount -%.2f", descuento));
                }
                sb.append("\n");
            }
        }
        if(i!=0) {
            sb.append(String.format("  Final Price: %.2f%n", totalProductos));
        }


        return sb.toString();
    }
}
