package es.upm.etsisi.poo.models.product;

public enum Services {
    TRANSPORT,
    SHOW,
    INSURANCE;

    @Override
    public String toString() {
        String string = null;
        switch (this) {
            case SHOW:
                string = "SHOW";
                break;
            case INSURANCE:
                string = "INSURANCE";
                break;
            case TRANSPORT:
                string = "TRANSPORT";
                break;
        }
        return string;
    }

}
