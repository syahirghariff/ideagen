package com.ideagen.calculator_service.calculatorservice;

import com.ideagen.calculator_service.service.CalculateService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CalculateServiceTest {

    @Autowired
    private CalculateService calculateService;

    private static final List<Expression> expressions  = new ArrayList<>();

    static {
        expressions.add(new Expression("( 11.5 + 15.4 ) + 10.1", 37));
        expressions.add(new Expression("23 - ( 29.3 - 12.5 )", 6.2));
        expressions.add(new Expression("10 - ( 2 + 3 * ( 7 - 5 ) )", 2));
    }

    @Test
    public void calculateTest(){

        for (Expression exp : expressions) {
            double result = calculateService.calculateByExpression(exp.getExpression());
            Assertions.assertThat(result).isEqualTo(exp.getResult());
        }

    }
}
