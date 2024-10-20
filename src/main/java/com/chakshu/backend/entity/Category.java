package com.chakshu.backend.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String name;
    private Byte sequence;
    private String description;
    private Double discount;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<Product> productList = new HashSet<>();



}
