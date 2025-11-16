package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.models.Client;
import es.upm.etsisi.poo.models.Cashier;

import java.util.ArrayList;
import java.util.Comparator;

public class ClientController {

    private ArrayList<Client> clientes;
    private CashierController cashierController;

    public ClientController(CashierController cashierController) {
        this.cashierController = cashierController;
        this.clientes = new ArrayList<>();
    }

    public void add(String nombre, String dni, String email, String cashId) {

        if (dniSearch(dni) != null) {
            System.out.println("Error. Ya existe un cliente con ese DNI.");
            return;
        }

        Cashier c = cashierController.searchId(cashId);
        if (c == null) {
            System.out.println("Error. Cajero no encontrado.");
            return;
        }

        Client nuevo = new Client(nombre, dni, email, c);
        clientes.add(nuevo);
        System.out.println(nuevo);
    }

    public void remove(String dni) {
        Client c = dniSearch(dni);

        if (c == null) {
            System.out.println("Error. No existe ese cliente.");
            return;
        }

        System.out.println(c);
        clientes.remove(c);
    }

    public void list() {
        Comparator<Client> comparador = Comparator.comparing(Client::getNombre);
        clientes.sort(comparador);
        for (int i = 0; i < clientes.size(); i++) {
            Client cliente = clientes.get(i);
            System.out.println(cliente);
        }
    }

    public Client dniSearch(String dni) {
        for (int i = 0; i < clientes.size(); i++) {
            Client cliente = clientes.get(i);
            if (cliente.getId().equals(dni)) {
                return cliente;
            }
        }
        return null;
    }
}

//o client add "<nombre>" <DNI> <email> <cashId>
//o client remove <DNI>
//o client list ( incluye el dato del cash que lo creo y ordenados por nombre)
