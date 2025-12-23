package es.upm.etsisi.poo.exceptions.general;

public class UnknownCommandException extends RuntimeException {
    public UnknownCommandException() {
        super("Comando no válido");
    }
}
