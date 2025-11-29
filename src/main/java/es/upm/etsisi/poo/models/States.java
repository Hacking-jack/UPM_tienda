package es.upm.etsisi.poo.models;

public enum States {
    VACIO,
    ACTIVO,
    CERRADO;

    @Override
    public String toString() {
        String string = null;
        switch (this) {
            case VACIO:
                string = "EMPTY";
                break;
            case ACTIVO:
                string = "OPEN";
                break;
            case CERRADO:
                string = "CLOSE";
                break;
        }
        return string;
    }
}
