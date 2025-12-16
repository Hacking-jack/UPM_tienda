package es.upm.etsisi.poo.controler;

import es.upm.etsisi.poo.dataBase.HumanDB;
import es.upm.etsisi.poo.models.human.Human;
import es.upm.etsisi.poo.models.human.Client;


public class ClientController {

    //TODO mirar formato DNI válido
    public static void add(String nombre, String dni, String email, String cashId) {
        if (HumanDB.existeId(dni)) {
            System.out.println("Error. Ya existe un cliente con ese DNI.");
            return;
        }
        if (!HumanDB.existeId(cashId)) {
            System.out.println("Error. Cajero no encontrado.");
            return;
        }
        Client u = new Client(nombre, dni, email, cashId);
        HumanDB.addUser(u);
        System.out.println(u);
    }

    public static void remove(String id) {
        Human p = HumanDB.findId(id);
        HumanDB.removeHuman(p);
    }

    public static void list() {
        System.out.println("Client:");
        for (Human human : HumanDB.list()) {
            if (human instanceof Client) {
                System.out.println("  " + human.toString());
            }
        }
    }

    public static Client searchId(String id) {
        return (Client) HumanDB.findId(id);
    }

    public static boolean existeId(String id) {
        return HumanDB.existeId(id);
    }


    public static boolean esDocumentoValido(String string) {
        if (string == null || string.length() != 9) return false;

        string = string.toUpperCase();
        char letraOriginal = string.charAt(8);
        String numeros;

        // Letras oficiales
        char[] letras = {
                'T','R','W','A','G','M','Y','F','P','D','X',
                'B','N','J','Z','S','Q','V','H','L','C','K','E'
        };

        if (string.matches("\\d{8}[A-Z]")) {    // es DNI
            numeros = string.substring(0, 8);
        } else if (string.matches("[XYZ]\\d{7}[A-Z]")) {    // es NIE -> hay que convertir X/Y/Z a número
            numeros = switch (string.charAt(0)) {
                case 'X' -> "0" + string.substring(1, 8);
                case 'Y' -> "1" + string.substring(1, 8);
                case 'Z' -> "2" + string.substring(1, 8);
                default -> null;
            };
            if (numeros == null) return false;
        } else {//  Formato incorrecto
            return false;
        }
        return letraOriginal == letras[Integer.parseInt(numeros) % 23];
    }



}