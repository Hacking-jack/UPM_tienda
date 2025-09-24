package es.upm.etsisi.poo.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ticket {
    private ArrayList<Product> products = new ArrayList<Product>();
    private final LocalDateTime creationDateTime = LocalDateTime.now();
    private String cashier;

}
