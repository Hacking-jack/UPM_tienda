package es.upm.etsisi.poo.dataBase;

import es.upm.etsisi.poo.exceptions.UserNotFoundException;
import es.upm.etsisi.poo.models.human.Human;

import java.util.ArrayList;
import java.util.Comparator;

public class HumanDB {
    //Tabla Humanos de base de datos, se guarda clientes y cajeros
    static private final ArrayList<Human> human = new ArrayList<>();


    static public void addUser(Human u) {
        human.add(u);
        human.sort(Comparator.comparing(Human::getNombre));
    }

    //Equivalente a Select * from Human where id = id;
    static public Human findId(String id) {
        for (Human p : human) {
            if (p.getId().equals(id))
                return (p);
        }
        throw new UserNotFoundException("No se encontró el usuario con id " + id);
    }

    static public boolean existeId(String id) {
        for (Human p : human) {
            if (p.getId().equals(id))
                return true;
        }
        return false;
    }

    //Equivalente a consulta de borrado de una fila de la tabla
    static public void removeHuman(Human p) {
        human.remove(p);
    }

    static public ArrayList<Human> list() {
        return human;
    }
}
