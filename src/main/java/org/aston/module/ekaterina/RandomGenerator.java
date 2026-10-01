package org.aston.module;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aston.module.yakov.interfaces.IBus;

public class RandomGenerator implements Input {
	private static Random rand;
	private int length;
	
	{
		rand = new Random();
	}

	public int getLength() {
		return length;
	}

	private String generateRouteNumber() {
		StringBuilder temp = new StringBuilder();
		char c = (char) rand.nextInt(50, 100);
		if (c == 'E' || c == 'M' || c == 'C' || c == 'T' || c == 'H')
			temp.append(c);
		int route = rand.nextInt(2500);
		temp.append(route);
		return temp.toString();
	}

	private String generateModel() {
		StringBuilder temp = new StringBuilder();
		int length = rand.nextInt(1, 5);
		for (int i = 0; i < length; i++) {
			char c = (char) rand.nextInt(65, 90);
			temp.append(c);
		}
		int modelNumber = rand.nextInt(9999);
		temp.append(modelNumber);
		return temp.toString().toUpperCase();
	}

	private float generateMileage() {
		return rand.nextInt(0, 200000) + rand.nextFloat();
	}

	@Override
	public IBus createBus(String... info) {
		String number = generateRouteNumber();
		String model = generateModel();
		float mileage = generateMileage();
		IBus item = new Bus(number, model, mileage);
		return item;
	}

	@Override
	public Collection<IBus> fillCollection(int length) {
		this.length = length;
		Collection<IBus> buses = Stream
				.generate(() -> createBus(null))
				.limit(length)
				.collect(Collectors.toList());
		return buses;
	}

}
