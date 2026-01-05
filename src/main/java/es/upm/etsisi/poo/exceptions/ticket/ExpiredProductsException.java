package es.upm.etsisi.poo.exceptions.ticket;

public class ExpiredProductsException extends RuntimeException {
    public ExpiredProductsException() {
        super("Uno o varios de los productos o servicios del ticket han expirado, recuerda que los servicios tienen " +
                "fecha limite y que las  para una comida el tiempo mínimo de antelación es de 3 dias y para reuniones" +
                "es de 12 horas, se han eliminado los productos caducados del ticket, pruebe a volver a imprimirlo");
    }
}
