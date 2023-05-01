package com.borisk58.personalizeddataapi.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/*
* Save shopper's personalized products list
* */
@Getter
@Setter
public class ShopperInputDto {
    private String shopperId;
    private List<ShopperProductInputDto> shelf;
}
