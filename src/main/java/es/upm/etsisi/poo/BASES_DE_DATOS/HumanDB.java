package es.upm.etsisi.poo.BASES_DE_DATOS;

import es.upm.etsisi.poo.models.Human;

import java.util.ArrayList;

public class HumanDB {
    //Tabla Humanos de base de datos, se guarda clientes y cajeros
    static private ArrayList<Human> human = new ArrayList<>();


    static public void addUser(Human u) {
        human.add(u);
    }

    //Equivalente a Select * from Human where id = id;
    static public Human findId(String id) {
        for (Human p : human) {
            if (p.getId().equals(id))
                return (p);
        }
        return null;
    }
    //EQuivalente a conulta de borrado de una fila de la tabla
    static public void removeHuman(Human p) {
        human.remove(p);
    }

    static public ArrayList<Human> list(){
        return human;
    }
}
