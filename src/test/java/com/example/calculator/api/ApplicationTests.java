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
		calculatorService.getStringDequeMap().clear();
	}

	@Test
	void createStack() {
		String idNewStack = calculatorService.createStack();
		Assertions.assertThat(calculatorService.listStacks()).isNotNull();
		Assertions.assertThat(calculatorService.listStacks().size()).isEqualTo(1);
		Assertions.assertThat(idNewStack).isNotBlank();
	}

	@Test
	void getStack() {
		String idNewStack = calculatorService.createStack();
		Deque<Integer> deque = calculatorService.getStack(idNewStack);

		Assertions.assertThat(deque).isNotNull();
		Assertions.assertThat(deque).isEmpty();
	}

	@Test
	void push() {
		String idNewStack = calculatorService.createStack();
		Deque<Integer> deque = calculatorService.push(idNewStack,1);
		deque = calculatorService.push(idNewStack,2);

		Assertions.assertThat(deque).isNotNull();
		Assertions.assertThat(deque).isNotEmpty();
		Assertions.assertThat(deque.peekLast()).isEqualTo(1);
		Assertions.assertThat(deque.peekFirst()).isEqualTo(2);
	}

	@Test
	void deleteStack() {
		String idNewStack = calculatorService.createStack();
		calculatorService.deleteStack(idNewStack);
		Assertions.assertThat(calculatorService.listStacks().isEmpty()).isTrue();

	}

	@Test
	void applyOperator() throws Exception {
		String idNewStack = calculatorService.createStack();
		Deque<Integer> deque = calculatorService.push(idNewStack, 1);
		deque = calculatorService.push(idNewStack, 2);;


		Assertions.assertThat(deque).isNotNull();
		Assertions.assertThat(deque).isNotEmpty();
		Assertions.assertThat(deque.peekLast()).isEqualTo(1);
		Assertions.assertThat(deque.peekFirst()).isEqualTo(2);

		deque = calculatorService.applyOperator(idNewStack, "+");
		Assertions.assertThat(deque.peekLast()).isEqualTo(3);
	}

	@Test
	void applyOperatorV2() throws Exception {
		String idNewStack = calculatorService.createStack();
		Deque<Integer> deque = calculatorService.push(idNewStack, 1);
		deque = calculatorService.push(idNewStack, 2);;
		deque = calculatorService.push(idNewStack, 4);;


		Assertions.assertThat(deque).isNotNull();
		Assertions.assertThat(deque).isNotEmpty();
		Assertions.assertThat(deque.peekLast()).isEqualTo(1);
		Assertions.assertThat(deque.peekFirst()).isEqualTo(4);

		deque = calculatorService.applyOperator(idNewStack, "+");
		Assertions.assertThat(deque.size()).isEqualTo(2);
		Assertions.assertThat(deque.peekLast()).isEqualTo(3);
		Assertions.assertThat(deque.peekFirst()).isEqualTo(4);
	}

	@Test
	void applyUnknownOperator() throws Exception {
		String idNewStack = calculatorService.createStack();
		Deque<Integer> deque = calculatorService.push(idNewStack,1);
		deque.push(2);


		Assertions.assertThat(deque).isNotNull();
		Assertions.assertThat(deque).isNotEmpty();
		Assertions.assertThat(deque.peekLast()).isEqualTo(1);
		Assertions.assertThat(deque.peekFirst()).isEqualTo(2);

		Assertions.assertThatThrownBy(() -> calculatorService.applyOperator(idNewStack,"^"))
				.isInstanceOf(RuntimeException.class)
				.hasMessage("UNKNOWN OPERATOR");
	}


}
