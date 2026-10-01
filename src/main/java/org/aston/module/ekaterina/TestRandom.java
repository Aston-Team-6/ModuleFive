package org.aston.module;

import java.util.List;

import org.aston.module.yakov.interfaces.IBus;

public class TestRandom {

	public static void main(String[] args) {
		RandomGenerator rg = new RandomGenerator();
		List<IBus> buses = (List<IBus>) rg.fillCollection(15);
		for (int i = 0; i < rg.getLength(); i++)
			System.out.println(buses.get(i).toString());

	}

}
