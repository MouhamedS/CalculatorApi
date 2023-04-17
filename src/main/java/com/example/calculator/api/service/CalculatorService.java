package com.example.calculator.api.service;

import java.util.Deque;

public interface CalculatorService {

    Deque getStack();

    Deque push(Integer integer);

    void clearStack();

    Deque applyOperator(String operator) throws Exception;
}
