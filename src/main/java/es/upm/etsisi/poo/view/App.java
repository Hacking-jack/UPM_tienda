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

import java.io.*;
import java.nio.channels.ScatteringByteChannel;
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

    public void main(String[] args) {
        App aplicacion = new App();
        aplicacion.iniciar();
        if (args.length == 1)
            runFromFile(args[0]);
        else
            runInteractive();
        //aplicacion.run();
        aplicacion.end();

    }

    private void iniciar() {

        this.productController = new ProductController();
        this.productCustomController = new ProductCustomController();
        this.productFoodMeetingController = new ProductFoodMeetingController();
        this.historyController = new HistorySalesController();
        this.cashierController = new CashierController();
        this.clientController = new ClientController();
        this.ticketController = new TicketController();
        System.out.println("Welcome to the ticket module App\nTicket module. Type 'help' to see commands");
    }

    public void runInteractive() {
        BufferedReader consoleReader =
                new BufferedReader(new InputStreamReader(System.in));

        run(consoleReader, false);
    }

    public void runFromFile(String filename) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            run(fileReader, true);
        } catch (IOException e) {
            System.out.println("No se pudo abrir archivo: " + e.getMessage());
        }
    }

    private void run(BufferedReader input, boolean esArchivo) {
        boolean bucle = true;
        String line;
        while (bucle) {
            try {
                while (bucle && (line = input.readLine()) != null) {

                    System.out.print("tUPM> ");
                    if(esArchivo){
                        System.out.println(line);
                    }

                    String[] args = line.split(" ");
                    String comando = ordenarSplit(args);

                    Command cmd = null;
                    switch (comando) {
                        case "client add":
                            removeComillas(args, 0);
                            cmd = new CommandClientAdd(args[0], args[1], args[2], args[3], clientController);
                            break;

                        case "client remove":
                            cmd = new CommandClientRemove(args[0], clientController);
                            break;

                        case "client list":
                            cmd = new CommandClientList(clientController);
                            break;

                        case "cash add":
                            if (!args[0].startsWith("\"")) {
                                removeComillas(args, 1);
                                cmd = new CommandCashAdd(args[0], args[1], args[2], cashierController);
                            } else {
                                removeComillas(args, 0);
                                cmd = new CommandCashAdd(null, args[0], args[1], cashierController);
                            }
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
                            if (args[2].equals(" ")) {
                                cmd = new CommandTicketNew(null, args[0], args[1], ticketController);
                            } else {
                                cmd = new CommandTicketNew(args[0], args[1], args[2], ticketController);
                            }
                            break;

                        case "ticket add":
                            String[] pers;
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
                            if (!args[0].startsWith("\"")) {
                                removeComillas(args, 1);
                                cmd = new CommandProductAdd(Integer.parseInt(args[0]), args[1], args[2],
                                        Double.parseDouble(args[3]), Integer.parseInt(args[4]), productController,
                                        productCustomController);
                            } else {
                                removeComillas(args, 0);
                                cmd = new CommandProductAdd(null, args[0], args[1], Double.parseDouble(args[2]),
                                        Integer.parseInt(args[3]), productController,
                                        productCustomController);
                            }
                            break;

                        case "prod update":
                            if (args[1].startsWith("\"")) {
                                removeComillas(args, 1);
                            }
                            cmd = new CommandProductUpdate(Integer.parseInt(args[0]), args[1], args[2], productController);
                            break;

                        case "prod addFood":
                            if (!args[0].startsWith("\"")) {
                                removeComillas(args, 1);
                                cmd = new CommandProductAddFood(Integer.parseInt(args[0]), args[1], Double.parseDouble(args[2]),
                                        args[3], Integer.parseInt(args[4]), productFoodMeetingController);
                            } else {
                                removeComillas(args, 0);
                                cmd = new CommandProductAddFood(null, args[0], Double.parseDouble(args[1]),
                                        args[2], Integer.parseInt(args[3]), productFoodMeetingController);
                            }
                            break;

                        case "prod addMeeting":
                            if (!args[0].startsWith("\"")) {
                                removeComillas(args, 1);
                                cmd = new CommandProductAddMeeting(Integer.parseInt(args[0]), args[1], Double.parseDouble(args[2]),
                                        args[3], Integer.parseInt(args[4]), productFoodMeetingController);
                            } else {
                                removeComillas(args, 0);
                                cmd = new CommandProductAddMeeting(null, args[0], Double.parseDouble(args[1]),
                                        args[2], Integer.parseInt(args[3]), productFoodMeetingController);
                            }
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
                    System.out.println(comando + ": ok");
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException ex) {
                System.out.println("Formato del comando incorrecto. Use help para ver los comandos");
            } catch (IOException e) {
                System.out.println(e.getMessage());
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
            juntarComillas(split);
            return command;
        }

        private String[] obtenerPers(String[] args) {
            int i = 4;// indice en el que empiezan las pers
            while (!args[i].equals(" ")) {
                i++;
            }
            String[] pers = new String[i - 4];
            for (int j = 4; j < i; j++) {
                pers[j - 4] = args[j];
            }
            return pers;
        }

        private static void juntarComillas(String[] split) {
            boolean inQuotes = false;
            StringBuilder current = new StringBuilder();
            int indice = 0;

            for (int i = 0; i < split.length; i++) {
                String part = split[i];
                if (!inQuotes) {
                    if (part.startsWith("\"") && !part.endsWith("\"")) {    //empiezan comillas
                        inQuotes = true;
                        current.append(part).append(" ");
                        indice = i;
                    }
                } else {
                    if (part.endsWith("\"")) {
                        current.append(part);
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

        private static void removeComillas(String[] array, int indice) {
            array[indice] = array[indice].substring(1, array[indice].length() - 1);
        }

        private static void reordenarArray(String[] split, int i1, int dif) {
            for (int i = i1 + 1; i < split.length - dif; i++) {
                split[i] = split[i + dif];
            }
            for (int i = split.length - dif; i < split.length; i++) {
                split[i] = " ";
            }
        }

    }