package es.upm.etsisi.poo.dataBase;

import es.upm.etsisi.poo.exceptions.user.UserNotFoundException;
import es.upm.etsisi.poo.models.user.User;

import java.util.ArrayList;
import java.util.Comparator;

public class UserDB {
    //Tabla Humanos de base de datos, se guarda clientes y cajeros
    private static final ArrayList<User> USERS = new ArrayList<>();


    public static void addUser(User u) {
        USERS.add(u);
        USERS.sort(Comparator.comparing(User::getNombre));
    }

    //Equivalente a Select * from Human where id = id;
    public static User findId(String id) {
        for (User p : USERS) {
            if (p.getId().equals(id)) {
                return (p);
            }
        }
        throw new UserNotFoundException("No se encontró el usuario con id " + id);
    }

    public static boolean existeId(String id) {
        for (User p : USERS) {
            if (p.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    //Equivalente a consulta de borrado de una fila de la tabla
    public static void removeUser(User p) {
        USERS.remove(p);
    }

    public static ArrayList<User> list() {
        return USERS;
    }
}
