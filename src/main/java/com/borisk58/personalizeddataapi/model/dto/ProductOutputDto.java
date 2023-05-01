package com.borisk58.personalizeddataapi.model.dto;

import com.borisk58.personalizeddataapi.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOutputDto {
    Product product;
    Double relevancyScore;
}
