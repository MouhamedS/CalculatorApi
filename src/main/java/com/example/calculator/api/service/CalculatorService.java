package com.example.calculator.api.service;

import java.util.Collection;
import java.util.Deque;
import java.util.Map;
import java.util.Set;

public interface CalculatorService {

    Map<String, Deque<Integer>> getStringDequeMap();
    Collection<Deque<Integer>> listStacks();

    Set<String> listStackId();
    String createStack();
    Deque getStack(String id);

    Deque push(String id, Integer integer);


    void deleteStack(String id);

    Deque applyOperator(String id, String operator) throws Exception;
}
