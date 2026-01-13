package es.upm.etsisi.poo.view;

import es.upm.etsisi.poo.commands.cash.*;
import es.upm.etsisi.poo.commands.clients.*;
import es.upm.etsisi.poo.commands.general.*;
import es.upm.etsisi.poo.commands.products.*;
import es.upm.etsisi.poo.commands.tickets.*;
import es.upm.etsisi.poo.commands.Command;
import es.upm.etsisi.poo.exceptions.general.UnknownCommandException;
import es.upm.etsisi.poo.exceptions.product.NotEnoughTimeException;
import es.upm.etsisi.poo.exceptions.user.UserNotFoundException;
import es.upm.etsisi.poo.exceptions.product.MaxAssistantOutOfBounds;
import es.upm.etsisi.poo.exceptions.product.PersonalitationUnformattedException;
import es.upm.etsisi.poo.exceptions.product.ProductNotFoundException;
import es.upm.etsisi.poo.exceptions.ticket.TicketNotFoundException;

import java.io.*;


public class App {

    public static void main(String[] args) {
        App aplicacion = new App();
        aplicacion.iniciar();
        if (args.length == 1) {
            runFromFile(args[0]);
        } else {
            runInteractive();
        }
        aplicacion.end();

    }

    private void iniciar() {
        System.out.println("Welcome to the ticket module App.\nTicket module. Type 'help' to see commands.");
    }

    public static void runInteractive() {
        BufferedReader consoleReader =
                new BufferedReader(new InputStreamReader(System.in));

        run(consoleReader, false);
    }

    public static void runFromFile(String filename) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            run(fileReader, true);
        } catch (IOException e) {
            System.out.println("No se pudo abrir archivo: " + e.getMessage());
        }
    }

    private static void run(BufferedReader input, boolean esArchivo) {
        boolean bucle = true;
        String line;
        while (bucle) {
            try {
                while (bucle) {
                    boolean esGeneral = false;
                    System.out.print("tUPM> ");
                    line = input.readLine();
                    if (line == null) {
                        break;
                    }

                    if (esArchivo) {
                        System.out.println(line);
                    }

                    String[] args = line.split(" ");
                    String comando = ordenarSplit(args);

                    Command cmd = null;
                    switch (comando) {
                        case "client add":
                            removeComillas(args, 0);
                            cmd = new CommandClientAdd(args[0], args[1], args[2], args[3]);
                            break;

                        case "client remove":
                            cmd = new CommandClientRemove(args[0]);
                            break;

                        case "client list":
                            cmd = new CommandClientList();
                            break;

                        case "cash add":
                            if (!args[0].startsWith("\"")) {
                                removeComillas(args, 1);
                                cmd = new CommandCashAdd(args[0], args[1], args[2]);
                            } else {
                                removeComillas(args, 0);
                                cmd = new CommandCashAdd(null, args[0], args[1]);
                            }
                            break;

                        case "cash remove":
                            cmd = new CommandCashRemove(args[0]);
                            break;

                        case "cash list":
                            cmd = new CommandCashList();
                            break;

                        case "cash tickets":
                            cmd = new CommandCashTickets(args[0]);
                            break;

                        case "ticket new":
                            if (args[2] == null) {
                                cmd = new CommandTicketNew(null, args[0], args[1], null);
                            } else {
                                if(args[2].startsWith("-")){
                                    cmd = new CommandTicketNew(null, args[0], args[1], args[2].charAt(1));
                                }else{
                                    if(args[3]==null){
                                        cmd = new CommandTicketNew(args[0], args[1], args[2], null);
                                    }else{
                                        cmd = new CommandTicketNew(args[0], args[1], args[2], args[3].charAt(1));
                                    }
                                }
                            }
                            break;

                        case "ticket add":
                            String[] pers;
                            pers = obtenerPers(args);
                            if ((pers != null) && (pers[0] == null)) {// formato erróneo de pers
                                throw new PersonalitationUnformattedException();
                            }
                            if(args[3]==null){
                                cmd = new CommandTicketAddProduct(args[0], args[1], args[2], null, pers);
                            }else{
                                cmd = new CommandTicketAddProduct(args[0], args[1], args[2], Integer.parseInt(args[3]), pers);
                            }
                            break;

                        case "ticket remove":
                            cmd = new CommandTicketRemoveProduct(args[0], args[1],args[2]);
                            break;

                        case "ticket print":
                            cmd = new CommandTicketPrint(args[0], args[1]);
                            break;

                        case "ticket list":
                            cmd = new CommandTicketList();
                            break;

                        case "prod add":
                            if (args[0].startsWith("\"")) {
                                    removeComillas(args, 0);
                                    if (args[3] == null) {
                                        cmd = new CommandProductAdd(null, args[0], args[1], Double.parseDouble(args[2]),
                                                null);
                                    } else {
                                        cmd = new CommandProductAdd(null, args[0], args[1], Double.parseDouble(args[2]),
                                                Integer.parseInt(args[3]));
                                    }
                            } else {
                                if(args[2]==null){
                                    cmd = new CommandProductAddService(args[0], args[1]);
                                }else{
                                    removeComillas(args, 1);
                                    if (args[4] == null) {
                                        cmd = new CommandProductAdd(args[0], args[1], args[2],
                                                Double.parseDouble(args[3]), null);
                                    } else {
                                        cmd = new CommandProductAdd(args[0], args[1], args[2],
                                                Double.parseDouble(args[3]), Integer.parseInt(args[4]));
                                    }
                                }
                            }
                            break;

                        case "prod update":
                            if (args[1].equals("NAME")) {
                                removeComillas(args, 2);
                            }
                            cmd = new CommandProductUpdate(args[0], args[1], args[2]);
                            break;

                        case "prod addFood":
                            if (!args[0].startsWith("\"")) {
                                removeComillas(args, 1);
                                cmd = new CommandProductAddFood(args[0], args[1], Double.parseDouble(args[2]),
                                        args[3], Integer.parseInt(args[4]));
                            } else {
                                removeComillas(args, 0);
                                cmd = new CommandProductAddFood(null, args[0], Double.parseDouble(args[1]),
                                        args[2], Integer.parseInt(args[3]));
                            }
                            break;

                        case "prod addMeeting":
                            if (!args[0].startsWith("\"")) {
                                removeComillas(args, 1);
                                cmd = new CommandProductAddMeeting(args[0], args[1], Double.parseDouble(args[2]),
                                        args[3], Integer.parseInt(args[4]));
                            } else {
                                removeComillas(args, 0);
                                cmd = new CommandProductAddMeeting(null, args[0], Double.parseDouble(args[1]),
                                        args[2], Integer.parseInt(args[3]));
                            }
                            break;

                        case "prod list":
                            cmd = new CommandProductList();
                            break;

                        case "prod remove":
                            cmd = new CommandProductRemove(args[0]);
                            break;

                        case "help":
                            cmd = new CommandHelp();
                            esGeneral = true;
                            break;

                        case "echo":
                            cmd = new CommandEcho(args[0]);
                            esGeneral = true;
                            break;

                        case "exit":
                            cmd = new CommandExit();
                            esGeneral = true;
                            break;

                        default:
                            break;
                    }
                    if (cmd == null) {
                        throw new UnknownCommandException();
                    } else {
                        bucle = cmd.execute();
                        if (!esGeneral) {
                            System.out.println(comando + ": ok\n");
                        }
                    }
                }
            } catch (ProductNotFoundException | TicketNotFoundException | UserNotFoundException | IOException |
                     MaxAssistantOutOfBounds | NotEnoughTimeException | UnknownCommandException ex) {//TODO rellenar el catch
                System.out.println(ex.getMessage()+"\n");
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException ex) {
                System.out.println("Formato del comando incorrecto. Use help para ver los comandos");
            }
        }

    }


    public void end() {
        System.out.println("Closing application.\nGoodbye!\n");
    }

    private static String ordenarSplit(String[] split) throws NumberFormatException {
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
                throw new UnknownCommandException();
            }
        }
        juntarComillas(split);
        return command;
    }

    private static String[] obtenerPers(String[] args) {
        if (args[4] != null) {
            int i = 4;// índice en el que empiezan las pers
            while (args[i] != null) {
                if (!args[i].startsWith("--p")) {
                    throw new PersonalitationUnformattedException();
                }
                i++;
            }
            String[] pers = new String[i - 4];
            for (int j = 4; j < i; j++) {
                pers[j - 4] = args[j].substring(3);
            }
            return pers;
        } else {
            return null;
        }
    }

    private static void juntarComillas(String[] split) {
        boolean inQuotes = false;
        StringBuilder current = new StringBuilder();
        int indice = 0;

        for (int i = 0; i < split.length; i++) {
            if (split[i] != null) {
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
    }

    private static void removeComillas(String[] array, int indice) {
        array[indice] = array[indice].substring(1, array[indice].length() - 1);
    }

    private static void reordenarArray(String[] split, int i1, int dif) {
        for (int i = i1 + 1; i < (split.length - dif); i++) {
            split[i] = split[i + dif];
        }
        for (int i = split.length - dif; i < split.length; i++) {
            split[i] = null;
        }
    }

}