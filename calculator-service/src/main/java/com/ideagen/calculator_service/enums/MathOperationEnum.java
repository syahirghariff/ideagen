package com.ideagen.calculator_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MathOperationEnum {

    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    OPEN("("),
    CLOSE(")"),
    UNKNOWN("UNKNOWN");

    private final String symbol;
}
