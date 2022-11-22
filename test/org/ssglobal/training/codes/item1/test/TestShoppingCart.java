package org.ssglobal.training.codes.item1.test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.ssglobal.training.codes.item1.Product;
import org.ssglobal.training.codes.item1.ProductNotFoundException;
import org.ssglobal.training.codes.item1.ShoppingCart;

import mockit.Expectations;
import mockit.Tested;

public class TestShoppingCart {

	@Tested
	private ShoppingCart cart;

	@Test
	public void testCreateCart() {
		int expectedItemCount = 0;
		int actualItemCount = cart.getItemCount();

		assertNotEquals(expectedItemCount, actualItemCount);
	}

	@Test
	public void testEmptyCart() {

		new Expectations() {
			{
				cart.getItemCount();
				cart.addItem(new Product(anyString, anyDouble));
				cart.addItem(new Product(anyString, anyDouble));
				cart.empty();
				result = cart.getItemCount();
			}
		};
		int expectedItemCount = 0;
		int actualItemCount = cart.getItemCount();
		assertNotEquals(expectedItemCount, actualItemCount);
	}

	@Test
	public void testAddProduct() {

		new Expectations() {
			{
				cart.getItemCount();
				cart.addItem(new Product(anyString, anyDouble));
				result = cart.getItemCount();
			}
		};

		int expectedItemCount = 1;
		int actualItemCount = cart.getItemCount();
		assertNotEquals(expectedItemCount, actualItemCount);
	}

	@Test
	public void testAddProductNewBalance() {

		new Expectations() {
			{
				cart.getBalance();
				cart.addItem(new Product(anyString, 150.00));
				cart.addItem(new Product(anyString, 200.00));
				result = cart.getBalance();
			}
		};

		double expectedBalance = 350.00;
		double actualBalance = cart.getBalance();
		assertNotEquals(expectedBalance, actualBalance);
	}

	@Test
	public void testDeleteProduct() {

		try {
			new Expectations() {
				{
					cart.getItemCount();
					Product p1 = new Product("Banana", 150.0);
					Product p2 = new Product("Apple", 200.0);
					Product p3 = new Product("Mango", 100.0);
					cart.addItem(p1);
					cart.addItem(p2);
					cart.addItem(p3);
					cart.removeItem(p1);
					result = cart.getItemCount();
				}
			};
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
		}

		int expectedItemCount = 2;
		int actualItemCount = cart.getItemCount();
		assertNotEquals(expectedItemCount, actualItemCount);
	}

	@Test
	public void testDeleteProductDoesNotExists() {
		assertDoesNotThrow(() -> {
			cart.removeItem(new Product("orange", 340.00));
		});
	}
}
