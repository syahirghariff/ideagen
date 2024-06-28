package com.ideagen.calculator_service.calculatorservice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Expression {
    private String expression;
    private double result;
}
