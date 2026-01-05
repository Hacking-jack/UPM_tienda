package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.product.ProductServiceController;
import java.time.LocalDateTime;

public class CommandProductAddService implements Command {
    private final String fechaStr;
    private final String tipoServicio;


    public CommandProductAddService(String fechaStr, String tipoServicio) {
        this.fechaStr = fechaStr;
        this.tipoServicio = tipoServicio;
    }

    @Override
    public boolean execute() {
        LocalDateTime dateMax = LocalDateTime.parse(fechaStr);
        ProductServiceController.addService(dateMax, tipoServicio);
        return true;
    }
}