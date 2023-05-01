package com.borisk58.personalizeddataapi.repositories;

import com.borisk58.personalizeddataapi.model.Product;
import com.borisk58.personalizeddataapi.model.ShopperProductLink;
import com.borisk58.personalizeddataapi.model.dto.ProductOutputDto;
import com.borisk58.personalizeddataapi.model.dto.ProductShoppersOutputDto;
import com.borisk58.personalizeddataapi.model.dto.ShopperProductsOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppersRepositoryImpl extends SimpleMongoRepository<ShopperProductLink, String> implements ShoppersRepository {
    public ShoppersRepositoryImpl(MongoEntityInformation<ShopperProductLink, String> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public void upsertShopper(ShopperProductLink shopper) {
        save(shopper);
    }

    @Override
    public void deleteShopper(String shopperId) {
        deleteById(shopperId);
    }

    @Override
    public ProductShoppersOutputDto getShoppersByProduct(String productId, int limit) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths("_id", "productLinks");
        ShopperProductLink data = new ShopperProductLink();
        data.setProductId(productId);
        Example<ShopperProductLink> example = Example.of(data, matcher);

        // get from DB by example, limited as requested
        List<ShopperProductLink> productLinks = findAll(example, PageRequest.of(0, limit)).getContent();

        ProductShoppersOutputDto result = new ProductShoppersOutputDto();
        result.setProductId(productId);
        result.setProductShoppers(productLinks.stream().map(ShopperProductLink::getShopperId).collect(Collectors.toList()));

        return result;
    }

    @Override
    public ShopperProductsOutputDto getProductsByShopper(String shopperId, String category, String brand, int limit) {

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths("_id", "productLinks");
        ShopperProductLink data = new ShopperProductLink();
        data.setShopperId(shopperId);
        Example<ShopperProductLink> example = Example.of(data, matcher);

        // get from DB by example, limited as requested
        List<ShopperProductLink> productLinks = findAll(example, PageRequest.of(0, limit)).getContent();

        Map<String, Double> scores = new Hashtable<>();
        productLinks.forEach(l -> scores.put(l.getProductId(), l.getRelevancyScore()));

        // get products from DB
        List<String> productIds = productLinks.stream().map(ShopperProductLink::getProductId).collect(Collectors.toList());
        List<Product> products = productsRepository.getProducts(productIds, category, brand);

        ShopperProductsOutputDto result = new ShopperProductsOutputDto();
        result.setShopperId(shopperId);
        result.setProducts(products
                .stream()
                .map(p -> new ProductOutputDto(p, scores.get(p.getProductId())))
                .collect(Collectors.toList()));

        return result;
    }
}
