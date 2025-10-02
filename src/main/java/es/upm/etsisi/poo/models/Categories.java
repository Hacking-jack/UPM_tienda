package es.upm.etsisi.poo.models;

public enum Categories {
    MERCH,
    PAPELERIA,
    ROPA,
    LIBRO,
    ELECTRONICA;
    public static double getDiscount(Categories categorie){
        switch (categorie){
            case MERCH:
                return 0;
            case PAPELERIA:
                return 0.05;
            case ROPA:
                return 0.07;
            case LIBRO:
                return 0.1;
            case ELECTRONICA:
                return 0.03;
            default:
                return 0;
        }
    }
}

