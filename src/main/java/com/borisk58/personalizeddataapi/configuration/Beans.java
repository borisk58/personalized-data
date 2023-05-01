package com.borisk58.personalizeddataapi.configuration;

import com.borisk58.personalizeddataapi.model.Product;
import com.borisk58.personalizeddataapi.model.ShopperProductLink;
import com.borisk58.personalizeddataapi.repositories.ProductsRepository;
import com.borisk58.personalizeddataapi.repositories.ProductsRepositoryImpl;
import com.borisk58.personalizeddataapi.repositories.ShoppersRepository;
import com.borisk58.personalizeddataapi.repositories.ShoppersRepositoryImpl;
import com.borisk58.personalizeddataapi.services.ShoppersService;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;

@Configuration
public class Beans {
    @Value("${spring.data.mongodb.uri}")
    String databaseUri;

    @Value("${spring.data.mongodb.database}")
    String databaseName;

    @Bean
    public MongoTemplate mongoTemplate() {
        ConnectionString connectionString = new ConnectionString(this.databaseUri);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        return new MongoTemplate(mongoClient, databaseName);
    }

    @Bean
    MongoEntityInformation<Product, String> productMetadata() {
        MongoRepositoryFactory factory = new MongoRepositoryFactory(mongoTemplate());
        return factory.getEntityInformation(Product.class);
    }

    @Bean
    MongoEntityInformation<ShopperProductLink, String> shopperMetadata() {
        MongoRepositoryFactory factory = new MongoRepositoryFactory(mongoTemplate());
        return factory.getEntityInformation(ShopperProductLink.class);
    }

    @Bean
    ProductsRepository productsRepository() {
        return new ProductsRepositoryImpl(productMetadata(), mongoTemplate());
    }

    @Bean
    ShoppersRepository shoppersRepository() {
        return new ShoppersRepositoryImpl(shopperMetadata(), mongoTemplate());
    }

    @Bean
    public ShoppersService shoppersService() {
        return new ShoppersService(productsRepository(), shoppersRepository());
    }
}
