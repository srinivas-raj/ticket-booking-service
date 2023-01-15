package com.veda.online.model;

import lombok.Data;

import java.util.Date;

@Data
public class MerchantDetails {
    private String cardNumber;
    private String cvv;
    private Date expiryDate;
}
