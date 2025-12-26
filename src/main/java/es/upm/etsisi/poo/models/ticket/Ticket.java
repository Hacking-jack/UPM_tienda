package es.upm.etsisi.poo.models.ticket;

import es.upm.etsisi.poo.dataBase.TicketDB;
import es.upm.etsisi.poo.models.product.Categories;
import es.upm.etsisi.poo.models.product.ProductBasic;
import es.upm.etsisi.poo.models.product.ProductMeetingFood;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class Ticket {
    private final ArrayList<ProductBasic> productBasics;
    private String idTicket;
    private final LocalDateTime date;
    private States estado;
    private TipoDeTicket tipoDeTicket;

    public States getEstado() {
        return estado;
    }

    public Ticket(String id, TipoDeTicket tipoDeTicket) {
        idTicket = id;
        estado = States.VACIO;
        date = LocalDateTime.now();
        productBasics = new ArrayList<>();
        this.tipoDeTicket=tipoDeTicket;
    }

    public Ticket(TipoDeTicket tipoDeTicket) {
        this(generarId(), tipoDeTicket);
    }


    public boolean addProduct(ProductBasic productBasic) {
        if (productBasics.isEmpty()) {
            estado = States.ACTIVO;
        }
        return (productBasics.size() < 100) && productBasics.add(productBasic);
    }

    public void addMeeting(ProductMeetingFood productMeeting) {
        int asistentes = 0;
        ProductMeetingFood tmp;
        for (ProductBasic productBasic : productBasics) {
            if (productBasic.getId() == productMeeting.getId()) {
                tmp = (ProductMeetingFood) productBasic;
                asistentes = tmp.getAssistants();
                productMeeting.setAsistentes(productMeeting.getAssistants() + asistentes);
                removeProduct(productBasic);
                break;
            }
        }
        addProduct(productMeeting);
    }

    public void removeProduct(ProductBasic p) {
        productBasics.removeIf(product -> product.getId() == p.getId());
    }

    public boolean hasDiscount(int counterStationary, int counterClothes, int counterBook, int counterElectronic,
                               Categories categories) {
        boolean correct = false;
        if (categories != null) {
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
        }
        return correct;
    }

    public void printAndClose() {
        if (estado != States.CERRADO) {
            //TODO aquí comprobar fechas de servicios y de eventos
            //comprobarCaducidad(); que lance error si hay algo caducado o que avise y lo borre del ticket
            estado = States.CERRADO;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("-yy-MM-dd-HH:mm");
        idTicket += LocalDateTime.now().format(formatter);
        print();
    }

    public String list() {
        return idTicket + " - " + estado.toString();
    }

    public void title() {
        System.out.println("  " + idTicket + "->" + estado.toString());
    }

    public String getStringPrint() {
        StringBuilder sb = new StringBuilder();

        productBasics.sort(Comparator.comparing(ProductBasic::getName));
        double precioTotal = 0;
        int counterStationary = 0, counterClothes = 0, counterBook = 0, counterElectronic = 0;

        for (ProductBasic p : productBasics) {
            precioTotal += p.getPrice();
            switch (p.getCategories()) {
                case STATIONERY -> counterStationary++;
                case CLOTHES -> counterClothes++;
                case BOOK -> counterBook++;
                case ELECTRONICS -> counterElectronic++;
            }
        }

        double descuentoTotal = 0;

        sb.append(String.format("Ticket : %s%n", idTicket));

        for (ProductBasic p : productBasics) {
            boolean discount = hasDiscount(counterStationary, counterClothes, counterBook, counterElectronic,
                    p.getCategories());
            double discountAmount = 0.0;
            if (discount) {
                discountAmount = Categories.getDiscount(p.getCategories()) * p.getPrice();
                descuentoTotal += discountAmount;
            }
            if (discountAmount == 0.0) {
                sb.append("  ").append(p).append("\n");
            } else {
                sb.append("  ").append(p)
                        .append(String.format(" **discount -%.2f%n", discountAmount));
            }
        }

        double precioFinal = precioTotal - descuentoTotal;
        sb.append(String.format("  Total price: %.2f%n", precioTotal));
        sb.append(String.format("  Total discount: %.2f%n", descuentoTotal));
        sb.append(String.format("  Final Price: %.2f", precioFinal));

        return sb.toString();
    }

    public void print() {
        System.out.println(getStringPrint());
        /*
        this.productBasics.sort(Comparator.comparing(ProductBasic::getName));
        double precioTotal = 0;
        int counterStationary = 0, counterClothes = 0, counterBook = 0, counterElectronic = 0;
        for (ProductBasic p : this.productBasics) {
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


        System.out.printf("Ticket : %s%n", this.idTicket);

        for (int i = 0; i < this.productBasics.size(); i++) {
            ProductBasic p = this.productBasics.get(i);
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
        System.out.printf("  Final Price: %.2f%n", precioFinal);*/
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "products=" + productBasics +
                ", idTicket='" + idTicket + '\'' +
                ", date=" + date +
                ", estado=" + estado +
                '}';
    }

    public static String generarId() {
        String string = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm-"))
                + String.format("%05d", (int) (Math.random() * 10000));
        if (TicketDB.existeId(string)) {
            return generarId();
        }
        return string;
    }

    public void setEstado(States estado) {
        this.estado = estado;
    }

    public String getIdTicket() {
        return idTicket;
    }
}
