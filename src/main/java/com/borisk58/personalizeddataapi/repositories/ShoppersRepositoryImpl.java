package com.borisk58.personalizeddataapi.repositories;

import com.borisk58.personalizeddataapi.model.ShopperProductLink;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

public class ShoppersRepositoryImpl extends SimpleMongoRepository<ShopperProductLink, String> implements ShoppersRepository {
    public ShoppersRepositoryImpl(MongoEntityInformation<ShopperProductLink, String> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }

    @Override
    public void upsertShopper(ShopperProductLink shopper) {

    }

    @Override
    public void deleteShopper(String shopperId) {

    }

    @Override
    public void getShoppersByProduct(String productId, int limit) {

    }
}
