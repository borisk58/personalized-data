package com.borisk58.personalizeddataapi.repositories;

import com.borisk58.personalizeddataapi.model.Product;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.List;

public class ProductsRepositoryImpl extends SimpleMongoRepository<Product, String> implements ProductsRepository {
    public ProductsRepositoryImpl(MongoEntityInformation<Product, String> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }

    @Override
    public void upsertProduct(Product product) {

    }

    @Override
    public void deleteProduct(String productId) {

    }

    @Override
    public List<Product> getProducts(Iterable<String> productIds) {
        return null;
    }
}
