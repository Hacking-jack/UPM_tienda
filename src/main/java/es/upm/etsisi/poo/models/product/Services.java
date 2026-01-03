package es.upm.etsisi.poo.models.product;

public enum Services {

    SHOW(1),
    INSURANCE(2),
    TRANSPORT(3);

    private final int id;

    Services(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

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

