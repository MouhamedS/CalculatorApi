package com.example.calculator.api.controller;

import com.example.calculator.api.service.CalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Deque;

@Tag(name ="Calculator API", description = "API to execute a RPN calculator")
@RestController
@RequiredArgsConstructor
@RequestMapping("/rpn")
public class CalculatorController {

    public final CalculatorService calculatorService;

    @Operation(summary = "Retrieve stack")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stack retrieved"),
            @ApiResponse(responseCode = "400", description = "Stack doesn't exist", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)

    })
    @GetMapping(value = "/stack")
    public Deque<Integer> getStack(){
        return calculatorService.getStack();
    }

    @Operation(summary = "Push stack")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stack retrieved"),
            @ApiResponse(responseCode = "400", description = "Stack does not exist", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)

    })
    @PostMapping(value = "/stack/push")
    public Deque<Integer> push(@RequestParam(value = "newValue") Integer integer){
        return calculatorService.push(integer);
    }

    @Operation(summary = "Clear stack")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stack retrieved"),
            @ApiResponse(responseCode = "400", description = "Stack does not exist", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)

    })
    @DeleteMapping(value = "/delete/stack")
    public void clearStack() {
        calculatorService.clearStack();
    }

    @Operation(summary = "Apply Operand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apply Operand done"),
            @ApiResponse(responseCode = "400", description = "Stack does not exist", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)

    })
    @PostMapping(value = "/op/{op}")
    public Deque<Integer> executeOperator(@PathVariable("op") String operator) throws Exception {
        return calculatorService.applyOperator(operator);
    }
}
