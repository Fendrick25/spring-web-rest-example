package com.example.demoservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_details")
public class ProductDetailEntity {

    @Id
    private String id;
    private String description;

    @OneToOne(mappedBy = "detail", fetch = FetchType.LAZY)
    private ProductEntity product;

}
