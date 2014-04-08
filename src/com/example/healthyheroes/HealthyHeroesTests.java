package com.example.healthyheroes;

import static org.junit.Assert.*;

import org.junit.Test;

public class HealthyHeroesTests {

	@Test
	public void FoodItemtest() {
		FoodItem fd_apple = new FoodItem("Apple", (double) 1.0, 20);
		FoodItem fd_orange = new FoodItem("Orange", (double) 1.25, 20, 10);
		
		
		for(int i = 0; i < 10; i++) {
			fd_apple.incrementNumberSold();
			fd_orange.incrementNumberSold();
		}
		assertEquals(fd_apple.getNumberSold(), 10);
		assertEquals(fd_orange.getNumberSold(), 20);
		assertEquals(fd_apple.limitReached(), false);
		assertEquals(fd_orange.limitReached(), true);
		
		for(int i = 0; i < 10; i++) {
			fd_apple.incrementNumberSold();
			fd_orange.incrementNumberSold();
		}

		assertEquals(fd_apple.getNumberSold(), 20);
		assertEquals(fd_orange.getNumberSold(), 20);
		assertEquals(fd_apple.limitReached(), true);
		assertEquals(fd_orange.limitReached(), true);

		for(int i = 0; i < 10; i++) {
			fd_apple.decrementNumberSold();
			fd_orange.decrementNumberSold();
		}
		assertEquals(fd_apple.getNumberSold(), 10);
		assertEquals(fd_orange.getNumberSold(), 10);

		for(int i = 0; i < 20; i++) {
			fd_apple.decrementNumberSold();
			fd_orange.decrementNumberSold();
		}

		assertEquals(fd_apple.getNumberSold(), 0);
		assertEquals(fd_orange.getNumberSold(), 0);
		assertEquals(fd_apple.limitReached(), true);
		assertEquals(fd_orange.limitReached(), true);
	}
}
