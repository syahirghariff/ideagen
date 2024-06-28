package com.ideagen.calculator_service.controller;

import com.ideagen.calculator_service.dto.CalculateRequest;
import com.ideagen.calculator_service.dto.CalculateResponse;
import com.ideagen.calculator_service.service.CalculateService;
import com.ideagen.calculator_service.util.ExpressionUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("calculate")
public class CalculateController {

    private final CalculateService calculateService;

    public CalculateController(CalculateService calculateService){
        this.calculateService = calculateService;
    }

    @Operation(summary = "To calculate simple mathematics",
            description = "Accepts a string parameter that will always consist of numbers and operators separated by spaces")
    @PostMapping
    public ResponseEntity<CalculateResponse> calculate(@RequestBody @Valid CalculateRequest request) {
        double results = calculateService.calculateByExpression(request.getExpression());
        return ResponseEntity.ok().body(new CalculateResponse(results));
    }
}
