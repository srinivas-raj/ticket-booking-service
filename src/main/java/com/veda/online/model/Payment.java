package com.veda.online.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Payment {
    private BigDecimal amount;
    private BigDecimal tax;
    private BigDecimal totalAmt;
    private String paymentType;
    private MerchantDetails merchantDetails;
}
