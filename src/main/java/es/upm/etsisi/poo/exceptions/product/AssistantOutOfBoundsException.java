package es.upm.etsisi.poo.exceptions.product;

public class AssistantOutOfBoundsException extends RuntimeException {
    public AssistantOutOfBoundsException(int maxAsis) {
        super("No se pueden añadir mas de " + maxAsis + " participantes." +
                " Intentalo de nuevo con un numero válido de participantes");
    }
}
