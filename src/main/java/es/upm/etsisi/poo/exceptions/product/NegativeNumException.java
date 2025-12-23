package es.upm.etsisi.poo.exceptions.product;

public class NegativeNumException extends IllegalArgumentException {
    public NegativeNumException() {
        super("Error, no se pueden añadir precios, productos o participantes negativos ");
    }

}
