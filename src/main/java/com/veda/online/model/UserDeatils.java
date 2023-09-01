package com.veda.online.model;

import lombok.Data;

import java.util.List;

@Data
public class UserDeatils {
    String userId;
    List<CartDetails> cartDetailsList;
}
