package es.upm.etsisi.poo.exceptions.product;

public class NotEnoughTimeException extends RuntimeException {
    public NotEnoughTimeException() {
        super("No cumple el requisito minimo de tiempo, para una comida el tiempo es de 3 dias " +
                "y para reuniones es de 12 horas");
    }
}
