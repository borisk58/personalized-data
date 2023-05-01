package com.borisk58.personalizeddataapi.repositories;

import com.borisk58.personalizeddataapi.model.Product;

import java.util.List;

public interface ProductsRepository {
    void upsertProduct(Product product);
    void deleteProduct(String productId);
    List<Product> getProducts(Iterable<String> productIds);
}
