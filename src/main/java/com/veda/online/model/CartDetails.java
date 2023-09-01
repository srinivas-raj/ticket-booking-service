package com.veda.online.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartDetails {
    String productId;
    BigDecimal price;
    BigDecimal promtionPrice;
    boolean promoApplied;
}
