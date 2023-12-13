package ru.netology.Bogachev.controller.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class OperationDTOInput {
    private final int sum;
    private final String currency;
    private final String merchant;
    private final int customerId;
    private final Integer cashbackAmount;
    private final String type;
}
