package ru.netology.Bogachev.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Customer {
    private String name;
    private int id;
    public Customer(int id, String name){
        this.id = id;
        this.name = name;
    }
}
