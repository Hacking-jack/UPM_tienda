package es.upm.etsisi.poo.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ticket {
    private ArrayList<Producto> products = new ArrayList<Producto>();
    private final LocalDateTime creationDateTime = LocalDateTime.now();
    private String cashier;

}
