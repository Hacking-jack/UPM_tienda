package es.upm.etsisi.poo.models.ticket;

import es.upm.etsisi.poo.dataBase.TicketDB;
import es.upm.etsisi.poo.exceptions.ticket.ProductNotInTicketException;
import es.upm.etsisi.poo.models.product.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Ticket {
    protected final ArrayList<Product> products;
    protected String idTicket;
    protected States estado;


    public Ticket(String id) {
        idTicket = id;
        estado = States.VACIO;
        products = new ArrayList<>();
    }

    public Ticket() {
        this(generarId());
    }

    public void removeProduct(Product p) {
        boolean encontrado = false;
        for (Product value : products) {
            if (Objects.equals(value.getId(), p.getId())) {
                encontrado = true;
                break;
            }
        }
        if(!encontrado) throw new ProductNotInTicketException(p.getId());

        products.removeIf(product -> product.getId().equals(p.getId()));
        if(products.isEmpty()){
            estado=States.VACIO;
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

    public String list() {
        return idTicket + " - " + estado.toString();
    }

    public void title() {
        System.out.println("  " + idTicket + "->" + estado.toString());
    }

    public void print() {
        System.out.println(getStringPrint());
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

    public States getEstado() {
        return estado;
    }

    public void setEstado(States estado) {
        this.estado = estado;
    }

    public String getIdTicket() {
        return idTicket;
    }

    protected boolean comprobarCaducidad(){
        boolean resul=true;
        for (Product product : products){
            if(product instanceof ProductService){
                if(((ProductService) product).isExpired()){
                    resul=false;
                    removeProduct(product);
                }
            }
            if(product instanceof ProductMeetingFood){
                if(((ProductMeetingFood) product).isExpired()){
                    resul=false;
                    removeProduct(product);
                }
            }
        }
        return resul;
    }

    public abstract String getStringPrint();

    public abstract boolean addProduct(Product product);

    public abstract void printAndClose();

    public abstract void addMeeting(ProductMeetingFood productMeetingFood);

    public abstract void addService(ProductService productService);
}
