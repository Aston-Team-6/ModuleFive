package org.aston.module.controller.generators;

import org.aston.module.controller.builder.BusBuilder;
import org.aston.module.dto.BusFromStorage;
import org.aston.module.interfaces.BusContract;
import org.aston.module.interfaces.BusStorageable;
import org.aston.module.interfaces.StorageDataTransferable;
import org.aston.module.models.Bus;

import java.io.IOException;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomGenerator implements BusStorageable
{
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
	
	private BusContract createBus() {
        return new BusBuilder().setNumber(generateRouteNumber())
				.setModel(generateModel())
				.setMileage(generateMileage()).build();
	}
	
	public Collection<BusContract> fillCollection(int length) {
		this.length = length;
		Collection<BusContract> buses = Stream
				.generate(() -> createBus())
				.limit(length)
				.collect(Collectors.toList());
		return buses;
	}

	@Override
	public StorageDataTransferable getData() throws IOException {
		return new BusFromStorage(fillCollection(length), length);
	}
}
