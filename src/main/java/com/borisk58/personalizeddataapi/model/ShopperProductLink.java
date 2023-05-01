package com.borisk58.personalizeddataapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "shoppers")
@Getter
@Setter
public class ShopperProductLink {
    String shopperId;
    String productId;
    Double relevancyScore;

    @MongoId
    String id;

    public String getId() {
        // todo: check shopperId and productId are assigned
        return String.format("%s::%s", shopperId, productId);
    }

}
