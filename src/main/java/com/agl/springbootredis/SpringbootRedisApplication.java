package com.agl.springbootredis;

import com.agl.springbootredis.entity.Product;
import com.agl.springbootredis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/product")
@EnableCaching
public class SpringbootRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRedisApplication.class, args);
	}

	@Autowired
	public ProductRepository productRepository;

	@PostMapping()
	public Product save(@RequestBody Product product){
		return productRepository.save(product);
	}

	@GetMapping("/{id}")
	@Cacheable(key = "#id", value = "Product")
	public  Product product(@PathVariable int id){
		return  productRepository.findById(id);
	}

	@GetMapping()
	@Cacheable(value = "Products")
	public List<Product> allProducts(){
		return  productRepository.findAll();
	}
}
