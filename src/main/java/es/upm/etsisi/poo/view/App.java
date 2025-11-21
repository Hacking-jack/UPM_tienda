package es.upm.etsisi.poo.view;

import es.upm.etsisi.poo.BASES_DE_DATOS.ProductDB;
import es.upm.etsisi.poo.BASES_DE_DATOS.TicketDB;
import es.upm.etsisi.poo.BASES_DE_DATOS.HumanDB;
import es.upm.etsisi.poo.commands.cash.CommandCashAdd;
import es.upm.etsisi.poo.commands.cash.CommandCashList;
import es.upm.etsisi.poo.commands.cash.CommandCashRemove;
import es.upm.etsisi.poo.commands.cash.CommandCashTickets;
import es.upm.etsisi.poo.commands.clients.CommandClientAdd;
import es.upm.etsisi.poo.commands.clients.CommandClientList;
import es.upm.etsisi.poo.commands.clients.CommandClientRemove;
import es.upm.etsisi.poo.commands.general.CommandEcho;
import es.upm.etsisi.poo.commands.general.CommandExit;
import es.upm.etsisi.poo.commands.general.CommandHelp;
import es.upm.etsisi.poo.commands.products.*;
import es.upm.etsisi.poo.commands.tickets.*;
import es.upm.etsisi.poo.controler.*;
import es.upm.etsisi.poo.commands.*;

import java.util.Scanner;


//TODO adaptar y terminar de estructurar bien el App al los controladores con las dependencias arregladas.
/*
Aqui va help, exit, etc... Las fnc visuales
help (lista los comandos)
echo “<texto>” (imprime el texto en el valor texto)
exit
 */
public class App {
    private HumanDB baseDeUsuarios;
    private TicketDB baseDeTickets;
    private ProductDB baseDeProductos;

    private ProductController productController;
    private TicketController ticketController;
    private CashierController cashierController;
    private ClientController clientController;
    private HistorySalesController historyController;
    private ProductFoodMeetingController productFoodMeetingController;
    private ProductCustomController productCustomController;

    public static void main(String[] args) {
        App aplicacion = new App();
        aplicacion.iniciar();
        aplicacion.run();
        aplicacion.end();

    }

    private void iniciar() {

        this.productController = new ProductController();
        this.historyController = new HistorySalesController();
        this.cashierController = new CashierController();
        this.clientController = new ClientController();
        this.ticketController = new TicketController();
        System.out.println("Welcome to the ticket module App\nTicket module. Type 'help' to see commands");
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        boolean bucle = true;
        while (bucle) {
            try {
                System.out.print("tUPM>");
                String line = scanner.nextLine();
                String[] args = line.split(" ");
                String comando = ordenarSplit(args);
                Command cmd = null;
                switch (comando) {
                    case "client add":
                        cmd = new CommandClientAdd(args[0], args[1], args[2], args[3], clientController);
                        break;

                    case "client remove":
                        cmd = new CommandClientRemove(args[0], clientController);
                        break;

                    case "client list":
                        cmd = new CommandClientList(clientController);
                        break;

                    case "cash add":
                        cmd = new CommandCashAdd(args[0], args[1], args[2], cashierController);
                        break;

                    case "cash remove":
                        cmd = new CommandCashRemove(args[0], cashierController);
                        break;

                    case "cash list":
                        cmd = new CommandCashList(cashierController);
                        break;

                    case "cash tickets":
                        cmd = new CommandCashTickets(args[0], cashierController);
                        break;

                    case "ticket new":
                        cmd = new CommandTicketNew(args[0], args[1], args[2], ticketController);
                        break;

                    case "ticket add":
                        String[] pers = new String[args.length];
                        pers = obtenerPers(args);
                        cmd = new CommandTicketAddProduct(args[0], args[1], args[2], Integer.parseInt(args[3]), pers,
                                ticketController, productController);
                        break;

                    case "ticket remove":
                        cmd = new CommandTicketRemoveProduct(args[0], args[1], Integer.parseInt(args[2]),
                                ticketController, productController);
                        break;

                    case "ticket print":
                        cmd = new CommandTicketPrint(args[0], args[1], ticketController);
                        break;

                    case "ticket list":
                        cmd = new CommandTicketList(ticketController);
                        break;

                    case "prod add":
                        cmd = new CommandProductAdd(Integer.parseInt(args[0]), args[1], args[2],
                                Double.parseDouble(args[3]), Integer.parseInt(args[4]), productController,
                                productCustomController);
                        break;

                    case "prod update":
                        cmd = new CommandProductUpdate(Integer.parseInt(args[0]), args[1], args[2], productController);
                        break;

                    case "prod addFood":
                        cmd = new CommandProductAddFood(Integer.parseInt(args[0]), args[1], Double.parseDouble(args[2]),
                                args[3], Integer.parseInt(args[4]), productFoodMeetingController);
                        break;

                    case "prod addMeeting":
                        cmd = new CommandProductAddMeeting(Integer.parseInt(args[0]), args[1], Double.parseDouble(args[2]),
                                args[3], Integer.parseInt(args[4]), productFoodMeetingController);
                        break;

                    case "prod list":
                        cmd = new CommandProductList(productController, productCustomController, productFoodMeetingController);
                        break;

                    case "prod remove":
                        cmd = new CommandProductRemove(Integer.parseInt(args[0]), productController);
                    case "help":
                        cmd = new CommandHelp();
                        break;

                    case "echo":
                        cmd = new CommandEcho(line);
                        break;

                    case "exit":
                        cmd = new CommandExit();
                        break;

                    default:
                        break;
                }
                bucle = cmd.execute();
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException ex) {
                System.out.println("Formato del comando incorrecto. Use help para ver los comandos");
            }
        }

    }

    public void end() {
        System.out.println("Closing application.\nGoodbye!\n");
    }

    private String ordenarSplit(String[] split) throws NumberFormatException {
        String command;
        String firsWord = split[0];
        if (firsWord.equalsIgnoreCase("client") || firsWord.equalsIgnoreCase("cash") ||
                firsWord.equalsIgnoreCase("ticket") || firsWord.equalsIgnoreCase("prod")) {
            command = firsWord + " " + split[1];
            reordenarArray(split, -1, 2);
        } else {
            if (firsWord.equalsIgnoreCase("exit") || firsWord.equalsIgnoreCase("help")
                    || firsWord.equalsIgnoreCase("echo")) {
                command = firsWord;
                reordenarArray(split, -1, 1);
            } else {
                throw new NumberFormatException("Formato de comando no válido");
            }
        }
        mergeQuotedInput(split);
        return command;
    }

    private String[] obtenerPers(String[] args) {
        int i=4;// indice en el que empiezan las pers
        while(!args[i].equals(" ")){
            i++;
        }
        String[] pers =new String[i-4];
        for(int j=4;j<i;j++){
            pers[j-4]=args[j];
        }
        return pers;
    }

    private static void mergeQuotedInput(String[] split) {
        boolean inQuotes = false;
        StringBuilder current = new StringBuilder();
        int indice = 0;

        for (int i = 0; i < split.length; i++) {
            String part = split[i];
            if (!inQuotes) {
                if (part.startsWith("\"") && !part.endsWith("\"")) {    //empiezan comillas
                    inQuotes = true;
                    current.append(part.substring(1)).append(" ");
                    indice = i;
                }
            } else {
                if (part.endsWith("\"")) {
                    current.append(part, 0, part.length() - 1);
                    split[indice] = current.toString();
                    reordenarArray(split, indice, i - indice);
                    current.setLength(0);
                    inQuotes = false;
                } else {
                    current.append(part).append(" ");
                }
            }
        }
    }

    private static void reordenarArray(String[] split, int i1, int dif) {
        for (int i = i1 + 1; i < split.length; i++) {
            split[i] = split[i + dif];
        }
        for(int i=split.length-dif-1; i< split.length;i++){
            split[i]=" ";
        }
    }

}