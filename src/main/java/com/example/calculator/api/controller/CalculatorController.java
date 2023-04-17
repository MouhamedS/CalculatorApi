package com.example.calculator.api.controller;

import com.example.calculator.api.service.CalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Deque;
import java.util.Set;

@Tag(name ="Calculator API", description = "API to execute a RPN calculator")
@RestController
@RequiredArgsConstructor
@RequestMapping("/rpn")
public class CalculatorController {

    public final CalculatorService calculatorService;

    @Operation(summary = "Create stack")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Stack created"),
            @ApiResponse(responseCode = "400", description = "Stack doesn't exist", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)

    })
    @PostMapping(value = "/stack")
    @ResponseStatus(HttpStatus.CREATED)
    public String createStack(){
        return calculatorService.createStack();
    }
    @Operation(summary = "Retrieve stack")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stack retrieved"),
            @ApiResponse(responseCode = "400", description = "Stack doesn't exist", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)

    })
    @GetMapping(value = "/stack/{stack_id}")
    public Deque<Integer> getStack(@PathVariable("stack_id") String id){
        return calculatorService.getStack(id);
    }

    @Operation(summary = "List of available stack")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Stack retrieved"),
            @ApiResponse(responseCode = "400", description = "Stack doesn't exist", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)

    })
    @GetMapping(value = "/stack")
    public Collection<Deque<Integer>> getListStack(){
        return calculatorService.listStacks();
    }
    @Operation(summary = "List of available stack ids")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of available stack ids retrieved"),
            @ApiResponse(responseCode = "400", description = "Stack doesn't exist", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)

    })
    @GetMapping(value = "/stack/ids")
    public Set<String> getListStackIds(){
        return calculatorService.listStackId();
    }
    @Operation(summary = "Push stack")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Push done"),
            @ApiResponse(responseCode = "400", description = "Stack does not exist", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)

    })
    @PostMapping(value = "/stack/{stack_id}")
    public Deque<Integer> push(@PathVariable("stack_id") String id, @RequestParam(value = "newValue") Integer integer){
        return calculatorService.push(id, integer);
    }

    @Operation(summary = "Clear stack")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stack deleted"),
            @ApiResponse(responseCode = "400", description = "Stack does not exist", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)

    })
    @DeleteMapping(value = "/delete/stack/{stack_id}")
    public void deleteStack(@PathVariable("stack_id")String id) {
        calculatorService.deleteStack(id);
    }

    @Operation(summary = "Apply Operand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apply Operand done"),
            @ApiResponse(responseCode = "400", description = "Stack does not exist", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)

    })
    @PostMapping(value = "/op/{op}/stack/{stack_id}")
    public Deque<Integer> executeOperator(@PathVariable("stack_id")String id,@PathVariable("op") String operator) throws Exception {
        return calculatorService.applyOperator(id, operator);
    }
}
