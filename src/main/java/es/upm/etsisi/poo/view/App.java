package es.upm.etsisi.poo.view;

import es.upm.etsisi.poo.controler.ProductController;
import es.upm.etsisi.poo.controler.TicketController;
import es.upm.etsisi.poo.models.Product;
import es.upm.etsisi.poo.models.Ticket;

import java.util.Objects;
import java.util.Scanner;

/*
Aqui va help, exit, etc... Las fnc visuales
help (lista los comandos)
echo “<texto>” (imprime el texto en el valor texto)
exit
 */
public class App {
    private Ticket ticket;
    private ProductController productController;
    private TicketController ticketController;

    public static void main(String[] args) {
        App aplicacion = new App();
        aplicacion.iniciar();
        aplicacion.run();

    }

    private void iniciar() {
        this.productController = new ProductController();
        this.ticket = new Ticket();
        this.ticketController = new TicketController(ticket);
    }

    private void run() {

        Scanner scanner = new Scanner(System.in);
        boolean bucle = true;
        while (bucle) {
            //System.out.print(TASK_APP);
            String line = scanner.nextLine();
            if (System.getenv("debugFile") != null)
                System.out.println(line);
            String[] lineSepSpace = line.split(" ");
            switch (lineSepSpace[0]) {
                case "help":
                    help();
                    break;
                case "exit":
                    bucle = false;
                    break;
                case "prod":
                    switch (lineSepSpace[1]) {
                        case "add":
                            reordenarSplitNombre(lineSepSpace,3);
                            productController.add(Integer.parseInt(lineSepSpace[2]), lineSepSpace[3], lineSepSpace[4].toUpperCase(), Double.parseDouble(lineSepSpace[5]));
                            break;
                        case "list":
                            productController.list();
                            break;
                        case "update":
                            if(Objects.equals(lineSepSpace[3], "NAME")){
                                reordenarSplitNombre(lineSepSpace,4);
                            }
                            productController.update(Integer.parseInt(lineSepSpace[2]), lineSepSpace[3], lineSepSpace[4]);
                            break;
                        case "remove":
                            productController.remove(Integer.parseInt(lineSepSpace[2]));
                            break;
                        default:
                            System.out.println("Comando no válido, usa help para ver lista de comandos");
                            break;
                    }
                    break;
                case "ticket":
                    switch (lineSepSpace[1]) {
                        case "new":
                            ticketController.newTicket();
                            break;
                        case "add":
                            if(productController.productoID(Integer.parseInt(lineSepSpace[2]))!=null) {
                                ticketController.add(productController.productoID(Integer.parseInt(lineSepSpace[2])), Integer.parseInt(lineSepSpace[3]));
                            }else{
                                System.out.println("ID no existe");
                            }
                            break;
                        case "remove":
                            if(productController.productoID(Integer.parseInt(lineSepSpace[2]))!=null) {
                                ticketController.remove(productController.productoID(Integer.parseInt(lineSepSpace[2])));
                            }else{
                                System.out.println("ID no existe");
                            }
                            break;
                        case "print":
                            ticketController.print();
                            break;
                        default:
                            System.out.println("Comando no válido, usa help para ver lista de comandos");
                            break;
                    }
                    break;
                case "echo":
                    System.out.println(line);
                    break;
                default:
                    System.out.println("Comando no válido, usa help para ver lista de comandos");
                    break;
            }
        }
    }

    public void reordenarSplitNombre(String[] array,int inicioNombre){
        int cont=0;
        boolean fin=false;
        while(!fin && cont+inicioNombre<array.length){
            if(array[inicioNombre+cont].charAt(array[inicioNombre+cont].length()-1)=='"'){
                fin=true;
            }else{
                cont++;
            }
        }
        if(!fin){
            System.out.println("El formato del nombre esta mal");
        }else{
            StringBuilder string = new StringBuilder();
            for(int i=inicioNombre;i<=inicioNombre+cont;i++){
                if(i==inicioNombre+cont){
                    string.append(array[i]);
                }else {
                    string.append(array[i]).append(" ");
                }

            }
            array[inicioNombre]=string.substring(1,string.length()-1);
            for(int i=inicioNombre+1;i < array.length-cont;i++){
                array[i]=array[i+cont];
            }
        }
    }

    public void help() {
        System.out.println("Commands:\n  prod add <id> \"" +
                "<name>\" <category> <price>\n  prod list\n" +
                "  prod update <id> NAME|CATEGORY|PRICE <value>\n" +
                "  prod remove <id>\n  ticket new\n  ticket add" +
                " <prodId> <quantity>\n  ticket remove <prodId>\n" +
                "  ticket print\n  echo \"<texto>\"\n  help\n  exit");
    }
}