package com.example.calculator.api.engine;

import java.math.BigDecimal;

public enum EnumOperand {
    MULTIPLY("*"){
        @Override
        Integer apply(Integer operand1, Integer operand2) {
            return operand1 * operand2;
        }
    },
    ADD("+") {
        @Override
        Integer apply(Integer operand1, Integer operand2) {
            return operand1 + operand2;
        }
    },
    MINUS("-") {
        @Override
        Integer apply(Integer operand1, Integer operand2) {
            return operand1 - operand2;
        }
    },
    DIVIDE("/") {
        @Override
        Integer apply(Integer operand1, Integer operand2) {
            return operand1 / operand2;
        }
    };
    public String operator;

    private EnumOperand(String operator){
        this.operator = operator;
    }
    public static Integer execute(String character, Integer operand1, Integer operand2) throws Exception {

        for (EnumOperand e : values()) {
            if (e.operator.equals(character)){
                return e.apply(operand1, operand2);
            }

        }
        throw new RuntimeException("UNKNOWN OPERATOR");
    }
    abstract Integer apply(Integer operand1, Integer operand2);
}