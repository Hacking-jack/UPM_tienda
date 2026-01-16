package es.upm.etsisi.poo.controler.user;

import es.upm.etsisi.poo.dataBase.UserDB;
import es.upm.etsisi.poo.exceptions.user.DuplicateIdentifierException;
import es.upm.etsisi.poo.exceptions.user.UserNotFoundException;
import es.upm.etsisi.poo.models.user.*;


public class ClientController {

    public static void addIndividualClient(String nombre, String dni, String email, String cashId) {
        if (UserDB.existeId(dni)) {
            throw new DuplicateIdentifierException(dni);
        }
        if (!UserDB.existeId(cashId)) {
            throw new UserNotFoundException("Error. Cajero no encontrado.");
        }
        Client client = new Client(nombre, dni, email, cashId);
        UserDB.addUser(client);
        System.out.println(client);
    }

    public static void addBusinessClient(String nombre, String cif, String email, String cashId) {
        if (UserDB.existeId(cif)) {
            throw new DuplicateIdentifierException(cif);
        }
        if (!UserDB.existeId(cashId)) {
            throw new UserNotFoundException("Error. Cajero no encontrado.");
        }
        ClientBusiness clientBusiness = new ClientBusiness(nombre, cif, email, cashId);
        UserDB.addUser(clientBusiness);
        System.out.println(clientBusiness);
    }

    public static void remove(String id) {
        User p = UserDB.findId(id);
        UserDB.removeUser(p);
    }

    public static void list() {
        System.out.println("Client:");
        for (User user : UserDB.list()) {
            if (user instanceof Client) {
                System.out.println("  " + user);
            }
        }
    }

    public static Client searchId(String id) {
        return (Client) UserDB.findId(id);
    }

    public static boolean existeId(String id) {
        return UserDB.existeId(id);
    }

    public static boolean isClient(String clientId){
        return UserDB.findId(clientId).getClass()==Client.class;
    }

    public static boolean isBusiness(String clientId){
        return UserDB.findId(clientId).getClass()== ClientBusiness.class;
    }


    public static boolean esDocumentoValido(String string) {
        if ((string == null) || (string.length() != 9)) {
            return false;
        }

        string = string.toUpperCase();
        char letraOriginal = string.charAt(8);
        String numeros;

        // Letras oficiales
        char[] letras = {
                'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X',
                'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'
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
            if (numeros == null) {
                return false;
            }
        } else {//  Formato incorrecto
            return false;
        }
        return letraOriginal == letras[Integer.parseInt(numeros) % 23];
    }


    //TODO Comprobar código
    public static boolean esCif(String cif) {
        if (cif == null) {
            return false;
        }

        cif = cif.toUpperCase();

        // Formato básico: 1 letra + 7 dígitos + 1 control
        if (!cif.matches("^[ABCDEFGHJNPQRSUVW][0-9]{7}[0-9A-J]$")) {
            return false;
        }

        char letraInicial = cif.charAt(0);
        char control = cif.charAt(8);

        int sumaPares = 0;
        int sumaImpares = 0;

        // Posiciones 1 a 7 (índices 1 a 7)
        for (int i = 1; i <= 7; i++) {
            int digito = Character.getNumericValue(cif.charAt(i));

            if ((i % 2) == 0) { // posiciones pares
                sumaPares += digito;
            } else { // posiciones impares
                int doble = digito * 2;
                sumaImpares += (doble > 9) ? (doble - 9) : doble;
            }
        }

        int sumaTotal = sumaPares + sumaImpares;
        int unidad = sumaTotal % 10;
        int digitoControl = (unidad == 0) ? 0 : (10 - unidad);

        char controlDigito = (char) ('0' + digitoControl);
        char controlLetra = "JABCDEFGHI".charAt(digitoControl);

        // Según la letra inicial, el control puede ser número, letra o ambos
        if ("ABEH".indexOf(letraInicial) >= 0) {
            return control == controlDigito;
        } else if ("KPQS".indexOf(letraInicial) >= 0) {
            return control == controlLetra;
        } else {
            return (control == controlDigito) || (control == controlLetra);
        }
    }


}