package com.example.demo.com.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection="Customer")
public class Customer {
    @Id
    private int id;
    private String regular;
    private int order;
    private int discount;
    private String email;
    private int price;


}
