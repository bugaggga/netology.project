package ru.netology.Bogachev.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class OperationsGetResponse {
    private final List<OperationDTO> operations;
}
