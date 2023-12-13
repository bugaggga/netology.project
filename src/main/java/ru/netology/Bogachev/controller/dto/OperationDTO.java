package ru.netology.Bogachev.controller.dto;

import lombok.Data;

@Data
public class OperationDTO {
    private final int id;
    private final int sum;
    private final String currency;
}
