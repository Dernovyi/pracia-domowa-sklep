package pl.dernovyi.praciadomowasklep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;



    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping("/products")
    public List<Product> get(){
        return productService.getProd();
    }

    @GetMapping("/addBasket/{id}") //dodawanie produktów do koszyka
    public List<Product> addToBasket(@PathVariable Integer id){
        Product product = productService.getProductById(id);
        productService.setBasket(product);
        return productService.getProductBasket();
    }

    @GetMapping("/basket")
    public List<Product> getBasket(){
        return productService.getProductBasket();
    }
}
