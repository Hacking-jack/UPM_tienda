package es.upm.etsisi.poo.commands.products;

import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.controler.product.ProductServiceController;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommandProductAddService implements Command {
    private final String fechaStr;
    private final String tipoServicio;


    public CommandProductAddService(String fechaStr, String tipoServicio) {
        this.fechaStr = fechaStr;
        this.tipoServicio = tipoServicio;
    }

    @Override
    public boolean execute() {
        LocalDate dateMax = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-M-d"));
        ProductServiceController.addService(dateMax, tipoServicio);
        return true;
    }
}