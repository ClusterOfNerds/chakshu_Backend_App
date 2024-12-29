package com.chakshu.backend.entity;

import com.chakshu.backend.enums.Status;
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
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Status status;
    private Status paymentStatus;
    private Integer quantityOrdered;
    private Integer shippingId;
    private Integer categoryId;
    @ManyToOne
    @JoinColumn
    private User user;
    @ManyToOne
    @JoinColumn
    private Inventory inventory;




}
