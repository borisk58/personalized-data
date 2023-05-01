package com.borisk58.personalizeddataapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "product")
@Getter
@Setter
public class Product {
    @MongoId
    private String productId;
    private String category;
    private String brand;
}
