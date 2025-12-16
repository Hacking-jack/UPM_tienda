package es.upm.etsisi.poo.models.ticket;

import es.upm.etsisi.poo.models.product.Categories;
import es.upm.etsisi.poo.models.product.ProductBasic;
import es.upm.etsisi.poo.models.product.ProductBasicMeeting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class Ticket {
    private final ArrayList<ProductBasic> productBasics;
    private String idTicket;
    private final LocalDateTime date;
    private States estado;

    public States getEstado() {
        return estado;
    }

    public Ticket(String id) {
        this.idTicket = id;
        this.estado = States.VACIO;
        this.date = LocalDateTime.now();
        this.productBasics = new ArrayList<>();
    }


    public boolean addProduct(ProductBasic productBasic) {
        if (productBasics.isEmpty())
            this.estado = States.ACTIVO;
        if (this.estado == States.CERRADO) {
            System.out.println("Error, no se pueden añadir productos a un ticket cerrado");
            return false;
        } else {
            if (this.productBasics.size() < 100)
                return productBasics.add(productBasic);
            else {
                System.out.println("Ticket lleno.");
                return false;
            }
        }
    }

    public void addMeeting(ProductBasicMeeting productMeeting) {
        int asistentes = 0;
        ProductBasicMeeting tmp;
        for (ProductBasic productBasic : productBasics) {
            if (productBasic.getId() == productMeeting.getId()) {
                tmp = (ProductBasicMeeting) productBasic;
                asistentes = tmp.getAsistentes();
                productMeeting.setAsistentes(productMeeting.getAsistentes() + asistentes);
                removeProduct(productBasic);
                break;
            }
        }
        addProduct(productMeeting);
    }

    public void removeProduct(ProductBasic productBasic) {
        while (this.productBasics.contains(productBasic)) {
            productBasics.remove(productBasic);
        }
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
        this.estado = States.CERRADO;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("-yy-MM-dd-HH:mm");
        this.idTicket += LocalDateTime.now().format(formatter);
        print();
    }

    public String list() {
        return this.idTicket + " - " + this.estado.toString();
    }

    public void title() {
        System.out.println("  "+this.idTicket + "->" + this.estado.toString());
    }

    public void print() {
        productBasics.sort(Comparator.comparing(ProductBasic::getName));
        double precioTotal = 0;
        int counterStationary = 0, counterClothes = 0, counterBook = 0, counterElectronic = 0;
        for (ProductBasic p : productBasics) {
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


        System.out.printf("Ticket : %s%n", idTicket);

        for (int i = 0; i < productBasics.size(); i++) {
            ProductBasic p = productBasics.get(i);
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

    public void setEstado(States estado) {
        this.estado = estado;
    }

    public String getIdTicket() {
        return idTicket;
    }
}
