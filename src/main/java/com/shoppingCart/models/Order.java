package com.shoppingCart.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "userId", nullable = false)
    private Long userId;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    @Column(name = "amount", nullable = true)
    private BigDecimal amount;
    private LocalDate date;
}
