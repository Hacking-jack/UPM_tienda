package es.upm.etsisi.poo.models;

public class ProductCustom extends Product{

    int maxPers;
    String[] listaPers;
    int persAct;

    public ProductCustom(String name, double price, Categories categories, int id, int maxPers){
        super(name, price, categories, id);
        this.maxPers = maxPers;
        listaPers=new String[this.maxPers];
        this.persAct=0;
    }

    public void addPers(String pers){
        if(persAct<maxPers){
            listaPers[persAct]=pers;
            persAct++;
        }else{
            System.out.println("No se ha podido añadir la personalización "+pers+", se ha alcanzado el maximo " +
                    "de personalizaciones");
        }
    }

    @Override
    public String toString() {
        StringBuilder s= new StringBuilder("{class:ProductPersonalized" +
                ", id:" + id + ", name:" + name + ", price:" + price + ", category:" + categories +
                ", price:" + price + ", maxPersonal:" + maxPers + "}");
        if(persAct>0){
            s.append("personalizationList:[");
            for(int i=0;i<persAct;i++){
                s.append(listaPers[i]);
                if(i!=persAct-1){
                    s.append(", ");
                }
            }
            s.append("]}");
        }
        return s.toString();
    }
}
