package es.upm.etsisi.poo.models;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Ticket {
    private ArrayList<Product> products;
    private String idTicket;
    //private double finalPrice;
    private LocalDateTime date;
    private States estado;


    public Ticket() {
        this.products = new ArrayList<Product>();
        this.date = LocalDateTime.now();
        Integer random5 = 10000 + new Random().nextInt(90000);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm-");
        this.idTicket = date.format(formatter).toString() + random5.toString();
    }

  /*  public Ticket(String idTicket, LocalDateTime date,
                  List<Product> products, double finalPrice, States estado) {
        this.products = new ArrayList<>(products);
        this.finalPrice = finalPrice;
        this.idTicket = idTicket;
        this.date = date;
        this.estado = States.VACIO;
    }*/


    //TODO despues de cada addProduct hay qeu hacer un print
    public boolean addPoduct(Product product) {
        if (products.isEmpty())
            this.estado = States.ACTIVO;
        if (this.products.size() < 100)
            return products.add(product);
        else {
            System.out.println("Ticket lleno.");
            return false;
        }
    }

    //TODO revisar si es util una resuesta
    public void removeProduct(Product product) {
        while (this.products.contains(product)) {
            products.remove(product);
        }
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
    public void closeTicket()
    {
        print();
        this.estado = States.CERRADO;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("-yy-MM-dd-HH:mm");
        this.idTicket += LocalDateTime.now().format(formatter);
    }
    public void print() {
        if (products.isEmpty()) {
            System.out.println("Error. El ticket está vacío.");
            return;
        }
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

        // ⭐⭐ LÍNEAS DE DEFINICIÓN AÑADIDAS ⭐⭐
        LocalDateTime now = LocalDateTime.now();
        String finalTicketId = this.idTicket + "-" + now.format(DateTimeFormatter.ofPattern("dd-MM-yy-HH:mm"));

        System.out.printf("Ticket : %s%n", finalTicketId); // ⭐ Se usa finalTicketId aquí

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

  /*  public ArrayList<Product> getProducts() {
        return products;
    }*/

    public LocalDateTime getDate() {
        return date;
    }

    /*public double getFinalPrice() {
        return finalPrice;
    }*/

    public String getIdTicket() {
        return idTicket;
    }
}
