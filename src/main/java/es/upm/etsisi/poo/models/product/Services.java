package es.upm.etsisi.poo.models.product;

public enum Services {

    SHOW,
    INSURANCE,
    TRANSPORT;
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

