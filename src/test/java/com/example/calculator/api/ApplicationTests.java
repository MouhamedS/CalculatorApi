package com.example.calculator.api;

import com.example.calculator.api.service.CalculatorService;
import com.example.calculator.api.service.CalculatorServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Deque;

@SpringBootTest
class ApplicationTests {


	CalculatorService calculatorService = new CalculatorServiceImpl();

	@BeforeEach
	void init() {
		calculatorService.getStack().clear();
	}

	@Test
	void getStack() {
		Deque<Integer> deque = calculatorService.getStack();

		Assertions.assertThat(deque).isNotNull();
		Assertions.assertThat(deque).isEmpty();
	}

	@Test
	void push() {
		Deque<Integer> deque = calculatorService.push(1);
		deque.push(2);

		Assertions.assertThat(deque).isNotNull();
		Assertions.assertThat(deque).isNotEmpty();
		Assertions.assertThat(deque.peekLast()).isEqualTo(1);
		Assertions.assertThat(deque.peekFirst()).isEqualTo(2);
	}

	@Test
	void clear() {
		Deque<Integer> deque = calculatorService.push(1);
		Assertions.assertThat(deque.peekLast()).isEqualTo(1);
		deque.clear();
		Assertions.assertThat(deque).isNotNull();
		Assertions.assertThat(deque).isEmpty();
	}

	@Test
	void applyOperator() throws Exception {
		Deque<Integer> deque = calculatorService.push(1);
		deque.push(2);


		Assertions.assertThat(deque).isNotNull();
		Assertions.assertThat(deque).isNotEmpty();
		Assertions.assertThat(deque.peekLast()).isEqualTo(1);
		Assertions.assertThat(deque.peekFirst()).isEqualTo(2);

		deque = calculatorService.applyOperator("+");
		Assertions.assertThat(deque.peekLast()).isEqualTo(3);
	}

	@Test
	void applyUnknownOperator() throws Exception {
		Deque<Integer> deque = calculatorService.push(1);
		deque.push(2);


		Assertions.assertThat(deque).isNotNull();
		Assertions.assertThat(deque).isNotEmpty();
		Assertions.assertThat(deque.peekLast()).isEqualTo(1);
		Assertions.assertThat(deque.peekFirst()).isEqualTo(2);

		Assertions.assertThatThrownBy(() -> calculatorService.applyOperator("^"))
				.isInstanceOf(RuntimeException.class)
						.hasMessage("UNKNOWN OPERATOR");
	}


}
