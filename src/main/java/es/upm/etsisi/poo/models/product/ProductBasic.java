package es.upm.etsisi.poo.models.product;



public class ProductBasic extends Product  {

    protected Categories categories;

    public ProductBasic(String id, String name, Categories categories, double price) {
        super(id, name, price);
        this.categories = categories;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public String getId() {
        return id;
    }
    //TODO revisar esto como hacemos la excepcion
//
//    @Override
//    public ProductBasic clone() {
//        return new ProductBasic(this.id, this.name, this.categories, this.price);
//    }
    @Override
    public ProductBasic clone() {
        try {
            return (ProductBasic) super.clone(); //TODO esto está mal, el super clone no tiene categories
        } catch (Exception e) {
            throw new AssertionError("No debería pasar, Product implementa Cloneable");
        }
    }
    @Override
    public String toString() {
        return "{class:Product, " + "id:" + id + ", name:'"
                + name + '\'' + ", category:" + categories + ", price:" + price + '}';
    }
}
