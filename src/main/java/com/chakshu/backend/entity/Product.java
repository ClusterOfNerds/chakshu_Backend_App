package com.chakshu.backend.entity;


import com.chakshu.backend.enums.Sizes;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String name;
    private String specs;
    private Set<Sizes> sizes = new HashSet<>();
    private Set<String> color;
    private Double price;
    private String imagePath;
    private Byte rating;
    private String feedback;
    @ManyToOne
    @JoinColumn
    private Category category;


}
