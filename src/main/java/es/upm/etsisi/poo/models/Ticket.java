package es.upm.etsisi.poo.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class Ticket {
    private final ArrayList<Product> products;
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
        this.products = new ArrayList<>();
    }


    public boolean addProduct(Product product) {
        if (products.isEmpty())
            this.estado = States.ACTIVO;
        if (this.estado == States.CERRADO) {
            System.out.println("Error, no se pueden añadir productos a un ticket cerrado");
            return false;
        } else {
            if (this.products.size() < 100)
                return products.add(product);
            else {
                System.out.println("Ticket lleno.");
                return false;
            }
        }
    }

    public void addMeeting(ProductMeeting productMeeting) {
        int asistentes = 0;
        ProductMeeting tmp;
        for (Product product : products) {
            if (product.getId() == productMeeting.getId()) {
                tmp = (ProductMeeting) product;
                asistentes = tmp.getAsistentes();
                productMeeting.setAsistentes(productMeeting.getAsistentes() + asistentes);
                removeProduct(product);
                break;
            }
        }
        addProduct(productMeeting);
    }

    public void removeProduct(Product product) {
        while (this.products.contains(product)) {
            products.remove(product);
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
        System.out.println(this.idTicket + "->" + this.estado.toString());
    }

    public void print() {
        products.sort(Comparator.comparing(Product::getName));
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


        System.out.printf("Ticket : %s%n", idTicket);

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
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
                "products=" + products +
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
