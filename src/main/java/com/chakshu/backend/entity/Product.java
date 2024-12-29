package com.chakshu.backend.entity;


import com.chakshu.backend.enums.Sizes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String name;
    private String specs;
    private String sizes;
    private String color;
    private Double price;
    private String imagePath;
    private Byte rating;
    private String feedback;
    @ManyToOne
    @JoinColumn(name = "fk_category_id")
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<Inventory> inventorySet = new HashSet<>();

}
