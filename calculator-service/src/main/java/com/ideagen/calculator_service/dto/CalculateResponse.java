package com.ideagen.calculator_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalculateResponse {
    private String result;

    public CalculateResponse(double result) {
        this.result = String.valueOf(result);
    }
}
