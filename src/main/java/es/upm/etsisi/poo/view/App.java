package es.upm.etsisi.poo.view;

import es.upm.etsisi.poo.controler.ProductController;
import es.upm.etsisi.poo.controler.TicketController;
import es.upm.etsisi.poo.models.Ticket;

import java.util.Scanner;

/*
Aqui va help, exit, etc... Las fnc visuales
help (lista los comandos)
echo “<texto>” (imprime el texto en el valor texto)
exit
 */
public class App 
{
    private Ticket ticket;
    private ProductController productController;
    private TicketController ticketController;
    public static void main( String[] args )
    {
        App aplicacion=new App();
        aplicacion.iniciar();
        aplicacion.run();

    }

    private void iniciar(){
        this.productController =new ProductController();
        this.ticket=new Ticket();
        this.ticketController=new TicketController(ticket);
    }

    private void run() {

        Scanner scanner=new Scanner(System.in);
        boolean bucle=true;
        while(bucle) {
            //System.out.print(TASK_APP);
            String line=scanner.nextLine();
            if (System.getenv("debugFile")!=null)
                System.out.println(line);
            String[] lineSepSpace = line.split(" ");
            switch (lineSepSpace[0]) {
                case "help":
                    help();
                    break;
                case "exit":
                    bucle=false;
                    break;
                case "prod":
                    switch (lineSepSpace[1]){
                        case "add":
                            break;
                        case "list":
                            break;
                        case "update":
                            break;
                        case "remove":
                            break;
                        default:
                            break;
                    }
                    break;
                case "ticket":
                    switch (lineSepSpace[1]){
                        case "new":
                            break;
                        case "add":
                            break;
                        case "remove":
                            break;
                        case "print":
                            break;
                        default:
                            break;
                    }
                    break;
                case "print":
                    break;
                case "echo":
                    System.out.println(line);
                    break;
                default:
                    break;
            }
        }
    }
    public void help(){
        System.out.println("Commands:\n  prod add <id> \"" +
                "<name>\" <category> <price>\n  prod list\n" +
                "  prod update <id> NAME|CATEGORY|PRICE <value>\n" +
                "  prod remove <id>\n  ticket new\n  ticket add" +
                " <prodId> <quantity>\n  ticket remove <prodId>\n" +
                "  ticket print\n  echo \"<texto>\"\n  help\n  exit");
    }
}