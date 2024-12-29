package com.chakshu.backend.entity;

import com.chakshu.backend.enums.Sizes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;
    @JoinColumn(name = "fk_product_id")
    @ManyToOne
    @JsonIgnore
    private Product product;
    private Integer categoryId;
    private String color;
    private String size;
    private Double price;
    private Integer quantity;
    private Integer totalNumberOfOrders;
    private Integer cartTouched = 0;
    private Integer wishTouched = 0;

}
