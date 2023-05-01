package com.borisk58.personalizeddataapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Hashtable;
import java.util.Map;

@Getter
@Setter
public class Shopper {
    private String shopperId;
    private final Map<String, ProductInfo> shelf = new Hashtable<>();
}
