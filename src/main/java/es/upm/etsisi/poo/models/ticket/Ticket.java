package es.upm.etsisi.poo.models.ticket;

import es.upm.etsisi.poo.dataBase.cache.TicketRepository;
import es.upm.etsisi.poo.exceptions.product.NotEnoughTimeException;
import es.upm.etsisi.poo.exceptions.ticket.*;
import es.upm.etsisi.poo.models.product.*;
import es.upm.etsisi.poo.models.ticket.printBehaviour.TicketPrintBehaviour;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Ticket<T extends Product> {
    protected Integer numId;
    protected final ArrayList<T> products;
    protected String idTicket;
    protected States estado;
    protected TicketPrintBehaviour<T> ticketPrintBehaviour;
    private Class<T> tipoPermitido;


    public Ticket(String id, TicketPrintBehaviour<T> ticketPrintBehaviour) {
        idTicket = id;
        estado = States.VACIO;
        products = new ArrayList<T>();
        this.ticketPrintBehaviour = ticketPrintBehaviour;
        tipoPermitido = ticketPrintBehaviour.getTipo();
    }
    public Ticket(Integer numId,String id, TicketPrintBehaviour<T> ticketPrintBehaviour) {
        this.numId=numId;
        idTicket = id;
        estado = States.VACIO;
        products = new ArrayList<T>();
        this.ticketPrintBehaviour = ticketPrintBehaviour;
    }
    public Ticket(TicketPrintBehaviour<T> ticketPrintBehaviour) {
        this(generarId(), ticketPrintBehaviour);
    }

    public void removeProduct(Product p) {
        if (products.removeIf(product -> product.getId().equals(p.getId()))) {
            if (products.isEmpty()) {
                estado = States.VACIO;
            }
        } else {
            throw new ProductNotInTicketException(p.getId());
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
    public boolean isNew(){
        return this.numId==null;
    }
    public static String generarId() {
        String string = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm-"))
                + String.format("%05d", (int) (Math.random() * 10000));
        if (TicketRepository.existeId(string)) {
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

    public ArrayList<T> getProducts() {
        return products;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public boolean comprobarCaducidad() {
        boolean resul = true;
        for (Product product : products) {
            if (product instanceof ProductService) {
                if (((ProductService) product).isExpired()) {
                    resul = false;
                    removeProduct(product);
                }
            }
            if (product instanceof ProductMeetingFood) {
                if (((ProductMeetingFood) product).isExpired()) {
                    resul = false;
                    removeProduct(product);
                }
            }
        }
        return resul;
    }

    public boolean addProduct(T product) {
        if (tipoPermitido.equals(ProductBasic.class) || tipoPermitido.equals(Product.class)) {
            if (estado == States.CERRADO) {
                throw new TicketClosedException();
            }
            if (estado == States.VACIO) {
                estado = States.ACTIVO;
            }
            return (products.size() < 100) && products.add(product);
        } else {
            throw new TicketTypeMismatchException();
        }
    }

    public void addMeeting(T productMeetingFood) {
        if (tipoPermitido.equals(ProductBasic.class) || tipoPermitido.equals(Product.class)) {
            if (productMeetingFood instanceof ProductMeetingFood) {
                if (estado == States.CERRADO) {
                    throw new TicketClosedException();
                }
                ProductMeetingFood pM = (ProductMeetingFood) productMeetingFood;
                for (T product : products) {
                    if (product.getId().equals(pM.getId())) {
                        if (product instanceof ProductMeetingFood) {
                            ProductMeetingFood pEnLista = (ProductMeetingFood) product;
                            pM.setAsistentes(pM.getAssistants() + pEnLista.getAssistants());
                        }
                        removeProduct(product);
                        break;
                    }
                }
                if (!pM.isExpired()) {
                    if (estado == States.VACIO) {
                        estado = States.ACTIVO;
                    }
                    addProduct(productMeetingFood);
                } else {
                    throw new NotEnoughTimeException();
                }
            } else {
                throw new TicketTypeMismatchException();
            }
        } else {
            throw new TicketTypeMismatchException();
        }
    }

    public void addService(T productService){
        if (tipoPermitido.equals(ProductService.class) || tipoPermitido.equals(Product.class)) {
            if (estado == States.CERRADO) {
                throw new TicketClosedException();
            }
            for (T product : products) {
                if (product.getId().equals(productService.getId())) {
                    throw new ServiceAlreadyInTicketException();
                }
            }
            if (products.isEmpty()) {
                estado = States.ACTIVO;
            }
            ProductService pS = (ProductService) productService;
            if (!pS.isExpired() && (products.size() < 100)) {
                products.add(productService);
                if (estado == States.VACIO) {
                    estado = States.ACTIVO;
                }
            }
        } else {
        throw new TicketTypeMismatchException();
    }
    }

    public String getStringPrint(){
        return ticketPrintBehaviour.getStringPrint(this);
    }

    public void printAndClose(){
        ticketPrintBehaviour.printAndClose(this);
    }

    public String list() {
        return idTicket + " - " + estado.toString();
    }

    public void title() {
        System.out.println("  " + idTicket + "->" + estado.toString());
    }

    public void print() {
        ticketPrintBehaviour.print(this);
    }


}
