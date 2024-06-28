package com.ideagen.calculator_service.util;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Pattern;

public class ExpressionUtil {

    public static final Pattern DIGIT_OPERANDS = Pattern.compile("\\d+(\\.\\d+)?|[+\\-*/()]");

    public static final Pattern MATHS = Pattern.compile("^[0-9+(\\.\\d+)?\\-*/()\\s]+$");

    public static boolean hasPrecedence(String operation1, String operation2) {

        if (StringUtils.containsAny(operation2, "(", ")")) {
            return false;
        }

        if (StringUtils.containsAny(operation1, "*", "/")
                && StringUtils.containsAny(operation2, "+", "-")) {
            return false;
        }

        return true;
    }


    public static double applyOperation(String operation, double valueB, double valueA) throws ArithmeticException {

        BigDecimal a = BigDecimal.valueOf(valueA);
        BigDecimal b = BigDecimal.valueOf(valueB);

        BigDecimal result = switch (operation) {
            case "+" -> a.add(b);
            case "-" -> a.subtract(b);
            case "*" -> a.multiply(b);
            case "/" -> Optional.of(b)
                    .filter(val -> !BigDecimal.ZERO.equals(val))
                    .map(a::divide)
                    .orElseThrow(()-> new ArithmeticException("Cannot divide by zero"));
            default -> throw new ArithmeticException("Invalid operations");
        };

        return result.doubleValue();
    }
}
