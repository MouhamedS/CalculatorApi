package com.example.calculator.api.service;

import com.example.calculator.api.engine.EnumOperand;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CalculatorServiceImpl  implements CalculatorService{
    Map<String, Deque<Integer>> stringDequeMap = new ConcurrentHashMap<>();
    //Deque<Integer> deque = new ArrayDeque<>();

    public Map<String, Deque<Integer>> getStringDequeMap() {
        return stringDequeMap;
    }

    @Override
    public Collection<Deque<Integer>> listStacks() {
        return stringDequeMap.values();
    }

    @Override
    public Set<String> listStackId() {
        return stringDequeMap.keySet();
    }

    @Override
    public String createStack() {
        String id = generateRandomString();
        stringDequeMap.put(id, new ArrayDeque<>());
        return id;
    }

    @Override
    public Deque getStack(String id) {
        return stringDequeMap.get(id);
    }

    @Override
    public Deque push(String id, Integer integer) {
        stringDequeMap.get(id).push(integer);
        return stringDequeMap.get(id);
    }

    @Override
    public void deleteStack(String id) {
        this.stringDequeMap.remove(id);
    }

    @Override
    public Deque applyOperator(String id, String operator) throws Exception {
        Deque<Integer> deque = stringDequeMap.get(id);
        if (deque == null || deque.isEmpty()){
            throw new RuntimeException("The stack id empty");
        }
        if (deque.size() == 1){
            return stringDequeMap.get(id);
        }
        Integer result = EnumOperand.execute(operator, deque.removeLast(), deque.removeLast());
        deque.offerLast(result);
        return deque;
    }

    private String generateRandomString(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 4;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }


}
