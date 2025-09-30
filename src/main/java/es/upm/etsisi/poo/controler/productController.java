package es.upm.etsisi.poo.controler;
import java.util.ArrayList;

import es.upm.etsisi.poo.models.Product;

/*
prod add <id> "<nombre>" <categoria> <precio> (agrega un producto con nuevo id)
prod list (lista productos actuales)
prod update <id> campo valor (campos: nombre|categoria|precio)
prod remove <id>
 */
public class productController {
    private ArrayList<Product> products = new ArrayList<Product>();
    public void add(int id, String name, String categories, double price){
        if (products.contains(id)){
            System.out.println("Error. Ya existe el id.");
        }else{
            Product product = new Product(name, price, categories, id);
            products.add(product);
            System.out.println(product.toString());
        }
        //Falta app.echo
    }
    public void list(){
        for (int i=0;i<products.size();i++){
            Product p = products.get(i);
            System.out.println(p.toString());
        }
    }
    public void update(int id, String campo, String valor){
        Product product = products.get(id);
        if (!(product==null)){
            System.out.println("Error. No existe el id para actualizar.");
        }else{
            switch (campo){
                case "NAME":
                    product.setName(valor);
                    break;
                case "CATEGORY":
                    product.setCategories(valor);
                    break;
                case "PRICE":
                    product.setPrice(Double.parseDouble(valor));
                    break;
                default:
                    System.out.println("Campo no valido");
                    break;
            }
        }
    }//Aqui falta un trycatch

}
