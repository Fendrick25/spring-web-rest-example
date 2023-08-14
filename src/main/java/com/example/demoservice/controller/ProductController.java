package com.example.demoservice.controller;

import com.example.demoservice.dto.request.CreateProductRequest;
import com.example.demoservice.dto.response.ProductResponse;
import com.example.demoservice.dto.response.ProductWithUsernameResponse;
import com.example.demoservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest createProductRequest){
        return new ResponseEntity<>(productService.createProduct(createProductRequest), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<ProductResponse>> getProductsByUsername(@PathVariable("username") String username){
        return new ResponseEntity<>(productService.getAllProductsByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<List<ProductWithUsernameResponse>> getProductsWithUsername(){
        return new ResponseEntity<>(productService.getAllProductWithUsername(), HttpStatus.OK);
    }

}
