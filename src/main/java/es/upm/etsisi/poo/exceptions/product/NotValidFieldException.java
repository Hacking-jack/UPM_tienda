package es.upm.etsisi.poo.exceptions.product;

public class NotValidFieldException extends RuntimeException {
    public NotValidFieldException(String field) {
        super("El campo: "+field+" no es un campo válido");
    }
}
