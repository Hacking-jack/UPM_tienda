package es.upm.etsisi.poo.models.product;

public enum Categories {
    MERCH,
    STATIONERY,
    CLOTHES,
    BOOK,
    ELECTRONICS;

    public static double getDiscount(Categories categorie) {
        switch (categorie) {
            case MERCH:
                return 0;
            case STATIONERY:
                return 0.05;
            case CLOTHES:
                return 0.07;
            case BOOK:
                return 0.1;
            case ELECTRONICS:
                return 0.03;
            default:
                return 0;
        }
    }
}

