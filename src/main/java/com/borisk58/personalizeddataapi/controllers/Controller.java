package com.borisk58.personalizeddataapi.controllers;

import com.borisk58.personalizeddataapi.model.Product;
import com.borisk58.personalizeddataapi.model.dto.ProductShoppersOutputDto;
import com.borisk58.personalizeddataapi.model.dto.ShopperInputDto;
import com.borisk58.personalizeddataapi.model.dto.ShopperProductsOutputDto;
import com.borisk58.personalizeddataapi.services.ShoppersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {
    @Autowired
    ShoppersService shoppersService;

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
    private ResponseEntity<ShopperProductsOutputDto> getProductsByShopper(
            @RequestParam(value = "shopperId") String shopperId,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "limit", required = false) Integer limit) {

        ShopperProductsOutputDto products = shoppersService.getProductInfo(shopperId, category, brand, limit);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /*
    * Data Team
    * */
    @PostMapping("/products")
    public ResponseEntity<Void> createOrUpdateProducts(@RequestBody Product product) {
        // todo: validate input and return BAD_REQUEST if bad
        try {
            shoppersService.createOrUpdateProduct(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/products/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@RequestParam(value = "productId") String productId) {
        try {
            shoppersService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/shoppers")
    public ResponseEntity<Void> createOrUpdateShopper(@RequestBody ShopperInputDto shopper) {
        // todo: validate input and return BAD_REQUEST if bad
        try {
            shoppersService.createOrUpdateShopper(shopper);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/shoppers")
    public ResponseEntity<Void> deleteShopper(String shopperId) {
        try {
            shoppersService.deleteShopper(shopperId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
 }
