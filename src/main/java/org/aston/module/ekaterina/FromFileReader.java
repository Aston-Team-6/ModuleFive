package org.aston.module;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.stream.Collectors;

import org.aston.module.yakov.interfaces.IBus;

public class FromFileReader implements Input {
	private String filename;

	public FromFileReader(String filename) {
		this.filename = filename;
	}

	@Override
	public IBus createBus(String... info) {
		
		String[] parameters = info[0].split(";");
		String number = parameters[0];
		String model = parameters[1];
		float mileage = Float.valueOf(parameters[2]);
		IBus item = new Bus(number, model, mileage);
		return item;
	}

	@Override
	public Collection<IBus> fillCollection(int length) {
		Collection<IBus> buses = null;
		try {
			buses = Files.readAllLines(Paths.get(filename)).stream()
										.map(line -> {return createBus(line);})
										.limit(length)
										.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buses;
	}

}
