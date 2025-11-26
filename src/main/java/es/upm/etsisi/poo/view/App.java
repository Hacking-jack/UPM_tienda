package es.upm.etsisi.poo.view;

import es.upm.etsisi.poo.BASES_DE_DATOS.ProductDB;
import es.upm.etsisi.poo.BASES_DE_DATOS.TicketDB;
import es.upm.etsisi.poo.BASES_DE_DATOS.HumanDB;
import es.upm.etsisi.poo.commands.cash.*;
import es.upm.etsisi.poo.commands.clients.*;
import es.upm.etsisi.poo.commands.general.*;
import es.upm.etsisi.poo.commands.products.*;
import es.upm.etsisi.poo.commands.tickets.*;
import es.upm.etsisi.poo.controler.*;
import es.upm.etsisi.poo.commands.Command;

import java.io.*;



/*
Aqui va help, exit, etc... Las fnc visuales
help (lista los comandos)
echo “<texto>” (imprime el texto en el valor texto)
exit
 */
public class App {

    public static void main(String[] args) {
        App aplicacion = new App();
        aplicacion.iniciar();
        if (args.length == 1)
            runFromFile(args[0]);
        else
            runInteractive();
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
        new CommandCashAdd("UW1","cash","mail").execute();
        new CommandClientAdd("cliente","1","mail", "UW1").execute();
        boolean bucle = true;
        String line;
        while (bucle) {
            try {
                while (bucle) {
                    System.out.print("tUPM> ");
                    line = input.readLine();
                    if (line == null) break;

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
                            System.out.println(comando + ": ok");
                            break;

                        case "client remove":
                            cmd = new CommandClientRemove(args[0]);
                            System.out.println(comando + ": ok");
                            break;

                        case "client list":
                            cmd = new CommandClientList();
                            System.out.println(comando + ": ok");
                            break;

                        case "cash add":
                            if (!args[0].startsWith("\"")) {
                                removeComillas(args, 1);
                                cmd = new CommandCashAdd(args[0], args[1], args[2]);
                            } else {
                                removeComillas(args, 0);
                                cmd = new CommandCashAdd(null, args[0], args[1]);
                            }
                            System.out.println(comando + ": ok");
                            break;

                        case "cash remove":
                            cmd = new CommandCashRemove(args[0]);
                            System.out.println(comando + ": ok");
                            break;

                        case "cash list":
                            cmd = new CommandCashList();
                            System.out.println(comando + ": ok");
                            break;

                        case "cash tickets":
                            cmd = new CommandCashTickets(args[0]);
                            System.out.println(comando + ": ok");
                            break;

                        case "ticket new":
                            if (args[2]==null) {
                                cmd = new CommandTicketNew(null, args[0], args[1]);
                            } else {
                                cmd = new CommandTicketNew(args[0], args[1], args[2]);
                            }
                            System.out.println(comando + ": ok");
                            break;

                        case "ticket add":
                            String[] pers;
                            pers = obtenerPers(args);
                            cmd = new CommandTicketAddProduct(args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), pers);
                            System.out.println(comando + ": ok");
                            break;

                        case "ticket remove":
                            cmd = new CommandTicketRemoveProduct(args[0], args[1], Integer.parseInt(args[2]));
                            System.out.println(comando + ": ok");
                            break;

                        case "ticket print":
                            cmd = new CommandTicketPrint(args[0], args[1]);
                            System.out.println(comando + ": ok");
                            break;

                        case "ticket list":
                            cmd = new CommandTicketList();
                            System.out.println(comando + ": ok");
                            break;

                        case "prod add":
                            if (!args[0].startsWith("\"")) {
                                removeComillas(args, 1);
                                if (args[4] == null) {
                                    cmd = new CommandProductAdd(Integer.parseInt(args[0]), args[1], args[2],
                                            Double.parseDouble(args[3]), null);
                                } else {
                                    cmd = new CommandProductAdd(Integer.parseInt(args[0]), args[1], args[2],
                                            Double.parseDouble(args[3]), Integer.parseInt(args[4]));
                                }
                            } else {
                                removeComillas(args, 0);
                                if (args[3] == null) {
                                    cmd = new CommandProductAdd(null, args[0], args[1], Double.parseDouble(args[2]),
                                            null);
                                } else {
                                    cmd = new CommandProductAdd(null, args[0], args[1], Double.parseDouble(args[2]),
                                            Integer.parseInt(args[3]));
                                }
                            }
                            System.out.println(comando + ": ok");
                            break;

                        case "prod update":
                            if (args[1].startsWith("\"")) {
                                removeComillas(args, 1);
                            }
                            cmd = new CommandProductUpdate(Integer.parseInt(args[0]), args[1], args[2]);
                            System.out.println(comando + ": ok");
                            break;

                        case "prod addFood":
                            if (!args[0].startsWith("\"")) {
                                removeComillas(args, 1);
                                cmd = new CommandProductAddFood(Integer.parseInt(args[0]), args[1], Double.parseDouble(args[2]),
                                        args[3], Integer.parseInt(args[4]));
                            } else {
                                removeComillas(args, 0);
                                cmd = new CommandProductAddFood(null, args[0], Double.parseDouble(args[1]),
                                        args[2], Integer.parseInt(args[3]));
                            }
                            System.out.println(comando + ": ok");
                            break;

                        case "prod addMeeting":
                            if (!args[0].startsWith("\"")) {
                                removeComillas(args, 1);
                                cmd = new CommandProductAddMeeting(Integer.parseInt(args[0]), args[1], Double.parseDouble(args[2]),
                                        args[3], Integer.parseInt(args[4]));
                            } else {
                                removeComillas(args, 0);
                                cmd = new CommandProductAddMeeting(null, args[0], Double.parseDouble(args[1]),
                                        args[2], Integer.parseInt(args[3]));
                            }
                            System.out.println(comando + ": ok");
                            break;

                        case "prod list":
                            cmd = new CommandProductList();
                            System.out.println(comando + ": ok");
                            break;

                        case "prod remove":
                            cmd = new CommandProductRemove(Integer.parseInt(args[0]));
                            System.out.println(comando + ": ok");
                            break;

                        case "help":
                            cmd = new CommandHelp();
                            break;

                        case "echo":
                            cmd = new CommandEcho(args[0]);
                            break;

                        case "exit":
                            cmd = new CommandExit();
                            break;

                        default:
                            break;
                    }
                    bucle = cmd.execute();
                }
            //} catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException ex) {
            //    System.out.println("Formato del comando incorrecto. Use help para ver los comandos");
            } catch (IOException e) {
                System.out.println(e.getMessage());
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
                throw new NumberFormatException("Formato de comando no válido");
            }
        }
        juntarComillas(split);
        return command;
    }

    private static String[] obtenerPers(String[] args) {
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
        for (int i = i1 + 1; i < split.length - dif; i++) {
            split[i] = split[i + dif];
        }
        for (int i = split.length - dif; i < split.length; i++) {
            split[i] = null;
        }
    }

}