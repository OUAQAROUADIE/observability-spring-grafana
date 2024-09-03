package com.example.observabilityspringgrafana.web;

import com.example.observabilityspringgrafana.entities.Product;
import com.example.observabilityspringgrafana.model.Post;
import com.example.observabilityspringgrafana.repository.ProductRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
public class ProductController {

    private ProductRepository productRepository;

    private RestClient restClient;
    public  ProductController(ProductRepository productRepository, RestClient.Builder  restClient){
        this.productRepository = productRepository;
        this.restClient = restClient
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();

    }

    @GetMapping("/products")
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    @GetMapping("/posts")
    public  List<Post> allPosts(){
        return restClient
                .get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Post>>() {});
    }
}
