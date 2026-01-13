package es.upm.etsisi.poo.exceptions.ticket;

public class NotSatisfiedMinimunRequirementsException extends RuntimeException {
    public NotSatisfiedMinimunRequirementsException() {

        super("No se pudo cerrar el ticket. Los tickets de producto deben de tener al menos un producto, los de" +
                " servicio un servicio y los mixtos uno de cada");
    }
}
