package com.borisk58.personalizeddataapi.model.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/*
* Get shoppers of product
* */

@Getter
@Setter
public class ProductShoppersOutputDto {
    String productId;
    List<String> productShoppers;
}
