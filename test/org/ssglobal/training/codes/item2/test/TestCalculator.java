package org.ssglobal.training.codes.item2.test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.ssglobal.training.codes.item2.Calculator;

import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;

public class TestCalculator {

	@Tested
	private Calculator calc;

	@Test
	public void testEvaluateNormal() {

		int expectedResult = 100;
		int actualResult = calc.evaluate("20+25+25+30");
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testEvaluateSolnNullArgs() {

		new MockUp<Calculator>() {

			@Mock
			public int evaluate(String exp) throws IllegalArgumentException {
				if (exp == null) {
					throw new IllegalArgumentException("Expression must not be null.");
				}

				if (exp.length() == 0) {
					return 0;
				}

				if (exp.contains("/") || exp.contains("*")) {
					throw new IllegalArgumentException("Expression can only evaluate addition.");
				}

				int result = 0;

				for (String summand : exp.split("\\+")) {
					int temp = 0;
					try {
						temp = Integer.parseInt(summand.trim());
					} catch (NumberFormatException e) {
						throw new IllegalArgumentException("can only evaluate integers.");
					}
					result += temp;
				}

				return result;
			}
		};

		assertThrows(IllegalArgumentException.class, () -> {
			int expectedResult = 0;
			int actualResult = calc.evaluate(null);
			assertEquals(expectedResult, actualResult);
		});
	}

	@Test
	public void testEvaluateSolnEmptyString() {

		new MockUp<Calculator>() {

			@Mock
			public int evaluate(String exp) throws IllegalArgumentException {
				if (exp == null) {
					throw new IllegalArgumentException("Expression must not be null.");
				}

				if (exp.length() == 0) {
					return 0;
				}

				if (exp.contains("/") || exp.contains("*")) {
					throw new IllegalArgumentException("Expression can only evaluate addition.");
				}

				int result = 0;

				for (String summand : exp.split("\\+")) {
					int temp = 0;
					try {
						temp = Integer.parseInt(summand.trim());
					} catch (NumberFormatException e) {
						throw new IllegalArgumentException("can only evaluate integers.");
					}
					result += temp;
				}

				return result;
			}
		};
	}

	@RepeatedTest(10)
	public void testEvaluateSolnOtherSummands() {

		new MockUp<Calculator>() {

			@Mock
			public int evaluate(String exp) throws IllegalArgumentException {
				if (exp == null) {
					throw new IllegalArgumentException("Expression must not be null.");
				}

				if (exp.length() == 0) {
					return 0;
				}

				if (exp.contains("/") || exp.contains("*")) {
					throw new IllegalArgumentException("Expression can only evaluate addition.");
				}

				int result = 0;

				for (String summand : exp.split("\\+")) {
					int temp = 0;
					try {
						temp = Integer.parseInt(summand.trim());
					} catch (NumberFormatException e) {
						throw new IllegalArgumentException("can only evaluate integers.");
					}
					result += temp;
				}

				return result;
			}
		};

		assertThrows(IllegalArgumentException.class, () -> {
			int expectedResult = 100;
			int actualResult = calc.evaluate("10+20+30+40A");
			assertEquals(expectedResult, actualResult);
		});
	}

	@ParameterizedTest
	@ValueSource(strings = { "10+3+9*23", "7+22-3+12", "16/32+45", "12+34-32*56", "10+23*3*4-22", "10+-23+44" })
	public void testEvaluateSolnOtherOperations(String params) {

		new MockUp<Calculator>() {

			@Mock
			public int evaluate(String exp) throws IllegalArgumentException {
				if (exp == null) {
					throw new IllegalArgumentException("Expression must not be null.");
				}

				if (exp.length() == 0) {
					return 0;
				}

				if (exp.contains("/") || exp.contains("*")) {
					throw new IllegalArgumentException("Expression can only evaluate addition.");
				}

				int result = 0;

				for (String summand : exp.split("\\+")) {
					int temp = 0;
					try {
						temp = Integer.parseInt(summand.trim());
					} catch (NumberFormatException e) {
						throw new IllegalArgumentException("can only evaluate integers.");
					}
					result += temp;
				}

				return result;
			}
		};

		assertDoesNotThrow(() -> {
			int expectedResult = 31;
			int actualResult = calc.evaluate(params);
			assertEquals(expectedResult, actualResult);
		});
	}

	@Test
	public void testEvaluateException() {
		new MockUp<Calculator>() {

			@Mock
			public int evaluate(String exp) throws IllegalArgumentException {
				if (exp == null) {
					throw new IllegalArgumentException("Expression must not be null.");
				}

				if (exp.length() == 0) {
					return 0;
				}

				if (exp.contains("/") || exp.contains("*")) {
					throw new IllegalArgumentException("Expression can only evaluate addition.");
				}

				int result = 0;

				for (String summand : exp.split("\\+")) {
					int temp = 0;
					try {
						temp = Integer.parseInt(summand.trim());
					} catch (NumberFormatException e) {
						throw new IllegalArgumentException("can only evaluate integers.");
					}
					result += temp;
				}

				return result;
			}
		};

		assertDoesNotThrow(() -> {
			int expectedResult = 31;
			int actualResult = calc.evaluate("ABC+DEF+HGI");
			assertEquals(expectedResult, actualResult);
		});

	}
}
