package com.chakshu.backend.entity;

import com.chakshu.backend.enums.Sizes;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;
    @JoinColumn
    @ManyToOne
    private Product product;
    private Integer categoryId;
    private String color;
    private Sizes size;
    private Double price;
    private Integer quantity;
    private Integer totalNumberOfOrders;
    private Integer cartTouched = 0;
    private Integer wishTouched = 0;



}
