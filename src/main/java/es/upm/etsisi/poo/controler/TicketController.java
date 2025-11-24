package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.BASES_DE_DATOS.TicketDB;
import es.upm.etsisi.poo.models.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

 // TODO tengo que pensar como interactuar con la base de datos
public class TicketController {

    private Ticket ticket;


    public void newTicket(String id){
        this.ticket=new Ticket(id);
    }

    public void addProduct(Product product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            if (!ticket.addProduct(product)) {
                System.out.println("No se pudieron añadir todos los productos, se han añadido " + i +
                        " hasta llegar al limite de 100");
                break;
            }
        }
    }
    public void addProductPers(Product product, int quantity, String[] pers){
        if(product instanceof ProductCustom){
            Product clone=product.clone();
            for (int i = 0; i < quantity; i++) {
                if (!ticket.addProduct(clone)) {
                    System.out.println("No se pudieron añadir todos los productos, se han añadido " + i +
                            " hasta llegar al limite de 100");
                    break;
                }
            }
            ((ProductCustom) product).addPers(pers);
        }else{
            System.out.println("No se puede personalizar un objeto no personalizable");
        }

    }
    public void remove(Product product) {
       ticket.removeProduct(product);
    }
    public void list(){
        ArrayList<Ticket> tickets = TicketDB.listProducts();
        for(Ticket t:tickets){
            System.out.println(t.toString());
        }
    }

    public String generarId(){
        return String.format(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm-"))
                + String.format("%05d", (int)(Math.random() * 100000)));
    }

    public void print(String ticketId){
        System.out.println(TicketDB.findId(ticketId));
    }



}