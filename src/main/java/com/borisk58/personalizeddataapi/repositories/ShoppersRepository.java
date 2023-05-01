package com.borisk58.personalizeddataapi.repositories;

import com.borisk58.personalizeddataapi.model.ShopperProductLink;
import com.borisk58.personalizeddataapi.model.dto.ProductShoppersOutputDto;
import com.borisk58.personalizeddataapi.model.dto.ShopperProductsOutputDto;

public interface ShoppersRepository {
    void upsertShopper(ShopperProductLink shopper);
    void deleteShopper(String shopperId);
    ProductShoppersOutputDto getShoppersByProduct(String productId, int limit);

    ShopperProductsOutputDto getProductsByShopper(String shopperId, String category, String brand, int limit);
}
