package com.borisk58.personalizeddataapi.repositories;

import com.borisk58.personalizeddataapi.model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsRepositoryImpl extends SimpleMongoRepository<Product, String> implements ProductsRepository {

    public ProductsRepositoryImpl(MongoEntityInformation<Product, String> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }

    @Override
    public void upsertProduct(Product product) {
        save(product);
    }

    @Override
    public void deleteProduct(String productId) {
        deleteById(productId);
    }

    @Override
    public List<Product> getProducts(List<String> productIds, String category, String brand) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths("_id");

        Product product = new Product();
        product.setCategory(category);
        product.setBrand(brand);
        Example<Product> example = Example.of(product);

        List<Product> products = findAll(example);
        return products.stream().filter(p -> productIds.contains(p.getProductId())).collect(Collectors.toList());
    }
}
