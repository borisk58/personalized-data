package com.borisk58.personalizeddataapi.services;

import com.borisk58.personalizeddataapi.model.Product;
import com.borisk58.personalizeddataapi.model.ShopperProductLink;
import com.borisk58.personalizeddataapi.model.dto.ProductShoppersOutputDto;
import com.borisk58.personalizeddataapi.model.dto.ShopperInputDto;
import com.borisk58.personalizeddataapi.model.dto.ShopperProductsOutputDto;
import com.borisk58.personalizeddataapi.repositories.ProductsRepository;
import com.borisk58.personalizeddataapi.repositories.ShoppersRepository;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class ShoppersService {
    @Value("${limit.default}")
    protected int defaultLimit;

    @Value("${limit.max}")
    protected int maxLimit;

    private final ProductsRepository productsRepository;
    private final ShoppersRepository shoppersRepository;

    public ShoppersService(ProductsRepository productsRepository, ShoppersRepository shoppersRepository) {
        this.productsRepository = productsRepository;
        this.shoppersRepository = shoppersRepository;
    }

    public void createOrUpdateShopper(ShopperInputDto shopper) {
        shopper.getShelf().forEach(p -> {
            ShopperProductLink shopperProductLink = new ShopperProductLink();
            shopperProductLink.setShopperId(shopper.getShopperId());
            shopperProductLink.setProductId(p.getProductId());
            shopperProductLink.setRelevancyScore(p.getRelevancyScore());
            this.shoppersRepository.upsertShopper(shopperProductLink);
        });
    }

    public List<ProductShoppersOutputDto> getShoppers(String productId, Integer limit) {
        return null;
    }

    public ShopperProductsOutputDto getProductInfo(String shopperId, String category, String brand, Integer limit) {
        int validLimit = checkLimit(limit);
        return shoppersRepository.getProductsByShopper(shopperId, category, brand, checkLimit(limit));
    }

    private int checkLimit(Integer limit) {
        return limit == null ? defaultLimit : limit > maxLimit ? maxLimit : limit;
    }

    public void deleteShopper(String shopperId) {
        shoppersRepository.deleteShopper(shopperId);
    }

    public void createOrUpdateProduct(Product product) {
        productsRepository.upsertProduct(product);
    }

    public void deleteProduct(String productId) {
        productsRepository.deleteProduct(productId);
    }
}
