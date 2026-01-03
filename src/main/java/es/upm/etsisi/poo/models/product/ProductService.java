package es.upm.etsisi.poo.models.product;

import java.time.LocalDateTime;

public class ProductService {
    private final double discount = 0.15;
    private LocalDateTime dateMax;
    private Services category;

    public ProductService(LocalDateTime dateMax, Services category) {
        this.dateMax = dateMax;
        this.category = category;
    }
    public int getServiceId() {
        return category.getId();
    }

    public Services getCategory() {
        return category;
    }

    public LocalDateTime getDateMax() {
        return dateMax;
    }

    public void setDateMax(LocalDateTime dateMax) {
        this.dateMax = dateMax;
    }

    public double getDiscount() {
        return this.discount;
    }

    @Override
    public String toString() {
        return "{class:ProductService"
                + ", id:" + getServiceId()
                + ", category:" + category
                + ", expiration:" + dateMax
                + "}";
    }

}
