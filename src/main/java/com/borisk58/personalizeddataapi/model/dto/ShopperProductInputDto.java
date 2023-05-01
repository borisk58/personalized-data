package com.borisk58.personalizeddataapi.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopperProductInputDto {
    String productId;
    Double relevancyScore;
}
