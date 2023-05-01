package com.borisk58.personalizeddataapi.services;

import com.borisk58.personalizeddataapi.model.dto.ProductShoppersOutputDto;
import com.borisk58.personalizeddataapi.model.dto.ShopperOutputDto;
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

    public List<ProductShoppersOutputDto> getShoppers(String productId, Integer limit) {
        int validLimit = checkLimit(limit);
        return null;
    }

    public List<ShopperOutputDto> getProductInfo(String shopperId, String category, String brand, Integer limit) {
        int validLimit = checkLimit(limit);
        return null;
    }

    private int checkLimit(Integer limit) {
        return limit == null ? defaultLimit : limit > maxLimit ? maxLimit : limit;
    }
}
