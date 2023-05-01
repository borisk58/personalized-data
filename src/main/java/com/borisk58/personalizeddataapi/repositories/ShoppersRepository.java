package com.borisk58.personalizeddataapi.repositories;

import com.borisk58.personalizeddataapi.model.ShopperProductLink;

public interface ShoppersRepository {
    void upsertShopper(ShopperProductLink shopper);
    void deleteShopper(String shopperId);
    void getShoppersByProduct(String productId, int limit);
}
