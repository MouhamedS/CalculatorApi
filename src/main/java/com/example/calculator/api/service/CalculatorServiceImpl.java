package com.example.calculator.api.service;

import com.example.calculator.api.engine.EnumOperand;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;

@Service
public class CalculatorServiceImpl  implements CalculatorService{

    Deque<Integer> deque = new ArrayDeque<>();



    @Override
    public Deque getStack() {
        return deque;
    }

    @Override
    public Deque push(Integer integer) {
        deque.push(integer);
        return deque;
    }

    @Override
    public void clearStack() {
        deque.clear();
    }

    @Override
    public Deque applyOperator(String operator) throws Exception {
        if (deque.isEmpty()){
            throw new RuntimeException("The stack id empty");
        }
        if (deque.size() == 1){
            return deque;
        }
        Integer result = EnumOperand.execute(operator, deque.removeLast(), deque.removeLast());
        deque.offerLast(result);
        return deque;
    }


}
