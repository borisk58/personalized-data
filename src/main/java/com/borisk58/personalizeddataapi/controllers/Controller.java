package com.borisk58.personalizeddataapi.controllers;

import com.borisk58.personalizeddataapi.model.Product;
import com.borisk58.personalizeddataapi.model.dto.ProductShoppersOutputDto;
import com.borisk58.personalizeddataapi.model.dto.ShopperInputDto;
import com.borisk58.personalizeddataapi.model.dto.ShopperOutputDto;
import com.borisk58.personalizeddataapi.services.ShoppersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {
    private ShoppersService shoppersService;

    /*
    * eCommerce
    * */
    @GetMapping("/shoppers")
    public ResponseEntity<List<ProductShoppersOutputDto>> getShoppers(
            @RequestParam(value = "productId") String productId,
            @RequestParam(value = "limit", required = false) Integer limit) {

        List<ProductShoppersOutputDto> shoppers = shoppersService.getShoppers(productId, limit);
        return new ResponseEntity<>(shoppers, HttpStatus.OK);
    }

    @GetMapping("/products")
    private ResponseEntity<List<ShopperOutputDto>> getProductsByShopper(
            @RequestParam(value = "shopperId") String shopperId,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "limit", required = false) Integer limit) {

        List<ShopperOutputDto> products = shoppersService.getProductInfo(shopperId, category, brand, limit);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /*
    * Data Team
    * */
    @PostMapping("/products")
    public void createOrUpdateProducts(Product product) {

    }

    @DeleteMapping("/products/delete/{productId}")
    public void deleteProduct() {

    }

    @PostMapping("/shoppers")
    public void createOrUpdateShopper(ShopperInputDto shopper) {

    }

    @DeleteMapping("/shoppers")
    public void deleteShopper(String shopperId) {

    }
 }
