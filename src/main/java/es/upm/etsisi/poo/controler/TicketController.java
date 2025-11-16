package es.upm.etsisi.poo.controler;
/*
Esto controla los tickets
ticket new (resetea ticket en curso)
ticket add <prodId> <cantidad> (agrega al ticket la cantidad de ese producto)
ticket remove <prodId> (elimina todas las apariciones del producto, revisa si existe el id )
ticket print (imprime factura)
 */

import es.upm.etsisi.poo.models.Categories;
import es.upm.etsisi.poo.models.Product;
import es.upm.etsisi.poo.models.Ticket;

public class TicketController {

    private Ticket ticket;
    private int counter;

    public TicketController(Ticket ticket) {
        this.ticket = ticket;
        this.counter = 0;
    }


    public void newTicket() {
        this.ticket = new Ticket();
        this.counter=0;
    }

    public void add(Product product, int quantity) {
        if (counter < 100) {
            boolean stop=false;
            for (int i = 0; i < quantity; i++){
                if(counter<100) {
                    ticket.getProducts().add(product);
                    counter++;
                }else{
                    stop=true;
                }
            }
            if(stop) System.out.println("No se pudieron añadir todos los productos, se han añadido hasta llegar" +
                                        "al limite de 100");
            print();

        } else {
            System.out.println("Ticket lleno.");
        }
    }

    public void remove(Product product) {
        while (ticket.getProducts().contains(product)) {
            ticket.getProducts().remove(product);
            counter--;
        }
    }

    public void print() {//Hay que terminarlo
        double precioTotal = 0, descuentoTotal = 0, precioFinal=0;
        int counterStationary=0,counterClothes=0,counterBook=0,counterElectronic=0;
        for (int i=0;i<ticket.getProducts().size();i++){
            if (ticket.getProducts().get(i).getCategories()==Categories.STATIONERY){
                counterStationary++;
            } else if (ticket.getProducts().get(i).getCategories()==Categories.CLOTHES) {
                counterClothes++;
            }else if (ticket.getProducts().get(i).getCategories()==Categories.BOOK){
                counterBook++;
            }else if(ticket.getProducts().get(i).getCategories()==Categories.ELECTRONICS){
                counterElectronic++;
            }
        }
        for (int i = ticket.getProducts().size()-1; i >=0; i--) {
            precioTotal += ticket.getProducts().get(i).getPrice();
            boolean discount=hasDiscount(counterStationary,counterClothes,counterBook,counterElectronic,
                    ticket.getProducts().get(i).getCategories());
            if (discount) {
                descuentoTotal += Categories.getDiscount(ticket.getProducts().get(i).getCategories()) *
                        ticket.getProducts().get(i).getPrice();
            }
            precioFinal = precioTotal - descuentoTotal;
            if (!discount || Categories.getDiscount(ticket.getProducts().get(i).getCategories()) == 0.0) {
                System.out.println(ticket.getProducts().get(i).toString());
            } else{
                System.out.print(ticket.getProducts().get(i).toString()) ;
                System.out.printf("**discount -%.2f%n", Categories.getDiscount
                        (ticket.getProducts().get(i).getCategories()) * ticket.getProducts().get(i).getPrice());
            }
        }
        System.out.println("Total price: " + precioTotal);
        System.out.printf("Total discount: %.2f%n", descuentoTotal);
        System.out.println("Final price: " + precioFinal);
    }
    public boolean hasDiscount(int counterStationary,int counterClothes,int counterBook,int counterElectronic,
                               Categories categories){
        boolean correct = false;
        switch (categories){
            case STATIONERY:
                if (counterStationary>=2){
                    correct=true;
                }
                break;
            case CLOTHES:
                if (counterClothes>=2){
                    correct=true;
                }
                break;
            case BOOK:
                if (counterBook>=2){
                    correct=true;
                }
                break;
            case ELECTRONICS:
                if (counterElectronic>=2){
                    correct=true;
                }
                break;
        }
        return correct;
    }
}
