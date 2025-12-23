package es.upm.etsisi.poo.commands.clients;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.user.ClientController;
import es.upm.etsisi.poo.exceptions.user.InvalidDocumentNumberException;

public class CommandClientAdd implements Command {

    private final String name;
    private final String id;
    private final String email;
    private final String cashId;

    public CommandClientAdd(String name, String id, String email, String cashId) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.cashId = cashId;
    }

    @Override
    public boolean execute() {
        if (ClientController.esDocumentoValido(this.id)) {
            ClientController.addIndividualClient(name, id, email, cashId);
        } else if (ClientController.esCif(this.id)) {
            //TODO ClientController.addBusinessClient(name, id, email, cashId);
        } else {
            throw new InvalidDocumentNumberException(id);
        }
        return true;
    }
}
