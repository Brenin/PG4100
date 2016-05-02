package test;

import static org.junit.Assert.*;

import org.junit.Test;

import car.Car;

public class CarTest {

	@Test
	public void testSetIsRented() {
		Car c = new Car();
		c.setIsRented(true);
		assertTrue(c.getIsRented());
	}

	@Test
	public void testGetIsRented() {
		Car c = new Car();
		assertFalse(c.getIsRented());
	}

	@Test
	public void testSetRegNr() {
		Car c = new Car();
		c.setRegNr("12345");
		assertEquals("XX12345", c.toString());
	}
	
	@Test
	public void testGetRegNr(){
		Car c = new Car();
		c.setRegNr("12345");
		assertEquals(c.getRegNr(), "12345");
	}

	@Test
	public void testToString() {
		Car c = new Car();
		c.setRegNr("12345");
		assertEquals("XX12345", c.toString());
	}
}
