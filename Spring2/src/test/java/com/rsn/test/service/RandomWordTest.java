package com.rsn.test.service;

import static org.junit.Assert.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class RandomWordTest {

	@Test
	public void test() {
		assertEquals(
				RandomStringUtils.random(10,true,true)
				.equals( RandomStringUtils.random(10,true,true)), false);
	}
	
	public static void main(String[] args) {
		for (int x=0; x < 28; x++) {
			System.out.println(RandomStringUtils.random(15,true,true));
		}
	}

}
