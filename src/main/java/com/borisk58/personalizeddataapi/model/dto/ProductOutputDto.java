package com.borisk58.personalizeddataapi.model.dto;

import com.borisk58.personalizeddataapi.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductOutputDto {
    Product product;
    Double relevancyScore;
}
