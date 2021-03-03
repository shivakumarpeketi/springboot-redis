package com.agl.springbootredis.repository;

import com.agl.springbootredis.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    public static final String PRODUCT = "Product";
    @Autowired
    private RedisTemplate redisTemplate;

    public Product save(Product product){
        redisTemplate.opsForHash().put(PRODUCT, String.valueOf(product.getId()), product);
        return product;
    }

    public List<Product> findAll(){
        System.out.println("find all fetching data from DB");
        HashOperations hashOperations = redisTemplate.opsForHash();
        return hashOperations.values(PRODUCT);
    }

    public  Product findById(int id){
        System.out.println("Called from DB");
        return (Product) redisTemplate.opsForHash().get(PRODUCT, String.valueOf(id));
    }
}
