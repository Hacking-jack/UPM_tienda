package es.upm.etsisi.poo.exceptions.human;

public class InvalidDocumentNumberException extends RuntimeException {
    public InvalidDocumentNumberException(String id) {
        super(id + " no es un identificador válido, en caso de clientes debe ser un DNI o un CIF, en caso de cajeros " +
                "debe ser del formato UW seguido de 7 dígitos ");
    }
}
