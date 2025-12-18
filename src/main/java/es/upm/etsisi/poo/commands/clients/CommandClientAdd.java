package es.upm.etsisi.poo.commands.clients;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.human.ClientController;
import es.upm.etsisi.poo.exceptions.human.InvalidDocumentNumberException;

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
        if(ClientController.esDocumentoValido(this.id)) {
            ClientController.addIndividualClient(name, id, email, cashId);
        } else if (ClientController.esCif(this.id)) {
            //TODO ClientController.addBusinessClient(name, id, email, cashId);
        }else{
            throw new InvalidDocumentNumberException("El numero "+id+" no es un DNI ni un CIF válido, compruébelo e " +
                    "inténtelo de nuevo");
        }
        return true;
    }
}
