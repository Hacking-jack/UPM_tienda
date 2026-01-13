package es.upm.etsisi.poo.models.product;

import java.time.LocalDate;

public class ProductService extends Product {
    private final double discount = 0.15;
    private LocalDate dateMax;
    private final Services category;

    public ProductService(int numS, LocalDate dateMax, Services category) {
        super(numS+"S");
        this.dateMax = dateMax;
        this.category = category;
    }
    public String getServiceId() {
        return id;
    }

    public Services getCategory() {
        return category;
    }

    public LocalDate getDateMax() {
        return dateMax;
    }

    public void setDateMax(LocalDate dateMax) {
        this.dateMax = dateMax;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "{class:ProductService"
                + ", id:" + getServiceId().substring(0, getServiceId().length()-1)
                + ", category:" + category
                + ", expiration:" + dateMax
                + "}";
    }

    //TODO esto no se yo
    public ProductService clone(){
        return new ProductService(Integer.parseInt(id.substring(0,id.length()-1)),dateMax, category);
    }

    public boolean isExpired(){
        return LocalDate.now().isAfter(dateMax);
    }

}
