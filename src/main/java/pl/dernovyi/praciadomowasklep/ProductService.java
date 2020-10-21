package pl.dernovyi.praciadomowasklep;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private List<Product> productList;
    private List<Product> productBasket = new ArrayList<>();

    @Value("${vat-info.vat}")
    private double vat;

    @Value("${vat-info.rabat}")
    private double rabat;

    public ProductService(){
        Product product1 = new Product("Telefon", 350.0 );
        Product product2 = new Product("Computer", 400.0);
        Product product3 = new Product("Sluhawki", 100.0);
        Product product4 = new Product("Clculator", 50.0);
        Product product5 = new Product("Telefon", 280.0);
        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
    }

    @EventListener(ApplicationReadyEvent.class)
    private List<Product> calculate(){
        List<Product> list = new ArrayList<>(this.productList);

        if(vat!=0){
          productList.forEach(product -> product.setPrice(product.getPrice() + (product.getPrice() * vat)));
          return productList;
        }
        if(rabat!=0){
            productList.forEach(product -> product.setPrice(product.getPrice() - (product.getPrice() * rabat)));
            return productList;
        }

       return list;
    }

    public List<Product> getProd(){
        return productList;
    }

    public Product getProductById(Integer id){
       return productList.get(id);
    }

    public void setBasket(Product product){
        productBasket.add(product);
    }

    public List<Product> getProductBasket(){
        return productBasket;
    }
}
