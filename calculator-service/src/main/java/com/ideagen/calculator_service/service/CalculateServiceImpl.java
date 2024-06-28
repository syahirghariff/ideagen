package com.ideagen.calculator_service.service;

import com.ideagen.calculator_service.util.ExpressionUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Stack;
import java.util.function.Supplier;
import java.util.regex.MatchResult;

@Service
public class CalculateServiceImpl implements CalculateService {

    @Override
    public Double calculateByExpression(String expression) {

        // To filter first if its Math Operations: If not throw
        if (!ExpressionUtil.MATHS.matcher(expression).matches()) {
            throw new ArithmeticException("Invalid operations");
        }

        // Define the pattern to match numbers and operators
        List<String> tokens = ExpressionUtil.DIGIT_OPERANDS.matcher(expression)
                .results()
                .map(MatchResult::group)
                .toList();

        if (CollectionUtils.isEmpty(tokens)){
            throw new ArithmeticException("Tokens is not available");
        }

        // First time me learning stack data structure 🤣
        Stack<Double> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String token: tokens) {
            if (NumberUtils.isCreatable(token)) {
                values.push(Double.parseDouble(token));
            } else if (StringUtils.equals(token, "(")) {
                operators.push(token);
            } else if (StringUtils.equals(token, ")")) {
                performValuesOperation(()-> !StringUtils.equals(operators.peek(), "("),
                            values, operators);
                operators.pop();
            } else {
                performValuesOperation(()-> CollectionUtils.isNotEmpty(operators)
                                && ExpressionUtil.hasPrecedence(token, operators.peek()),
                        values, operators);
                operators.push(token);
            }
        }

        performValuesOperation(()-> CollectionUtils.isNotEmpty(operators),
                values, operators);

        return values.pop();
    }

    private void performValuesOperation(Supplier<Boolean> condition, Stack<Double> values, Stack<String> operators) {
        while (condition.get()) {
            if (CollectionUtils.size(values) < 2) {
                throw new ArithmeticException("Invalid Operations");
            }
            double value = ExpressionUtil.applyOperation(operators.pop(), values.pop(), values.pop());
            values.push(value);
        }
    }
}
