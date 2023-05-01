package com.borisk58.personalizeddataapi.controllers;

import com.borisk58.personalizeddataapi.model.ProductInfo;
import com.borisk58.personalizeddataapi.model.Shopper;
import com.borisk58.personalizeddataapi.services.ShoppersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {
    private ShoppersService shoppersService;

    @GetMapping("/shoppers")
    public ResponseEntity<List<Shopper>> getShoppers(
            @RequestParam(value = "productId") String productId,
            @RequestParam(value = "limit", required = false) Integer limit) {

        List<Shopper> shoppers = shoppersService.getShoppers(productId, limit);
        return new ResponseEntity<>(shoppers, HttpStatus.OK);
    }

    @GetMapping("/products")
    private ResponseEntity<List<ProductInfo>> getProductsByShopper(
            @RequestParam(value = "shopperId") String shopperId,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "limit", required = false) Integer limit) {

        List<ProductInfo> products = shoppersService.getProductInfo(shopperId, category, brand, limit);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
