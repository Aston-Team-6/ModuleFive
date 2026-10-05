package org.aston.module.controller.generators;

import org.aston.module.controller.builder.BusBuilder;
import org.aston.module.controller.validator.BusDataValidator;
import org.aston.module.dto.BusFromStorage;
import org.aston.module.interfaces.BusContract;
import org.aston.module.interfaces.BusStorageable;
import org.aston.module.interfaces.StorageDataTransferable;
import org.aston.module.value.objects.Length;

import java.io.IOException;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomGenerator implements BusStorageable
{
	private static Random rand;
	private BusDataValidator validator;
	private Length length;
	public RandomGenerator(Length length)
	{
		this.length = length;
	}

	{
		rand = new Random();
		validator = new BusDataValidator();
	}

	public Length getLength() {
		return length;
	}

	private String generateRouteNumber() {
		StringBuilder temp = new StringBuilder();
		char c = (char) rand.nextInt(50, 100);
		if (c == 'E' || c == 'M' || c == 'C' || c == 'T' || c == 'H')
			temp.append(c);
		int route = rand.nextInt(2500);
		temp.append(route);
		String number = temp.toString();
		validator.validateNumber(number);
		return number;
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
		String model = temp.toString().toUpperCase();;
		validator.validateModel(model);
		return model;
	}

	private Float generateMileage() {
		Float mileage = rand.nextInt(0, 200000) + rand.nextFloat();
		validator.validateMileage(mileage);
		return mileage;
	}
	
	private BusContract createBus() {
        return new BusBuilder().setNumber(generateRouteNumber())
				.setModel(generateModel())
				.setMileage(generateMileage()).build();
	}
	
	public Collection<BusContract> fillCollection() {
		Collection<BusContract> buses = Stream
				.generate(() -> createBus())
				.limit(length.getValue())
				.collect(Collectors.toList());
		return buses;
	}

	@Override
	public StorageDataTransferable getData() throws IOException {
		return new BusFromStorage(fillCollection(), length.getValue());
	}
}
