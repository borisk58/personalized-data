package com.borisk58.personalizeddataapi.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
* Get products of shopper
* */

@Getter
@Setter
public class ShopperProductsOutputDto {
    String shopperId;
    List<ProductOutputDto> products;
}
