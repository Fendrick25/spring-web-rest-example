package com.example.demoservice.service;

import com.example.demoservice.business.Product;
import com.example.demoservice.dto.request.CreateProductRequest;
import com.example.demoservice.dto.response.ProductResponse;
import com.example.demoservice.dto.response.ProductWithUsernameResponse;
import com.example.demoservice.entity.ProductDetailEntity;
import com.example.demoservice.entity.ProductEntity;
import com.example.demoservice.repository.ProductJpaRepository;
import com.example.demoservice.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductJpaRepository productJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public ProductResponse createProduct(CreateProductRequest createProductRequest){
        Product product = Product.builder()
                .name(createProductRequest.getName())
                .price(createProductRequest.getPrice())
                .description(createProductRequest.getDescription())
                .quantity(createProductRequest.getQuantity())
                .build();
        product.initialize();


        ProductEntity savedProductEntity = productJpaRepository.save(mapToProductEntity(product));

        Product savedProduct = mapToProduct(savedProductEntity);

        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(savedProduct, productResponse);
        return productResponse;

    }

    public List<ProductWithUsernameResponse> getAllProductWithUsername(){
       return productJpaRepository.findAllProductWithUsername().stream()
                .map(productWithUsername -> ProductWithUsernameResponse.builder()
                        .username(productWithUsername.getUsername())
                        .name(productWithUsername.getName())
                        .price(productWithUsername.getPrice())
                        .description(productWithUsername.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    public List<ProductResponse> getAllProductsByUsername(String username){
        return userJpaRepository.findByUsername(username).getProducts().stream()
                .map(this::mapToProduct)
                .map(product -> {
                    ProductResponse productResponse = new ProductResponse();
                    BeanUtils.copyProperties(product, productResponse);
                    return productResponse;
                })
                .collect(Collectors.toList());

    }

    private Product mapToProduct(ProductEntity productEntity){
        Product product = new Product();
        BeanUtils.copyProperties(productEntity, product);
        product.calculateTotalValue();
        product.setDescription(productEntity.getDetail().getDescription());
        return product;
    }



    private ProductEntity mapToProductEntity(Product product){
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(product, productEntity);
        productEntity.setDetail(ProductDetailEntity.builder()
                .id(UUID.randomUUID().toString())
                .description(product.getDescription())
                .build());

        return productEntity;
    }

}
