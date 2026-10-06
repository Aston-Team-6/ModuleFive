package org.aston.module.controller.generators;

import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aston.module.controller.builder.BusBuilder;
import org.aston.module.interfaces.BusContract;
import org.aston.module.value.objects.Length;

public class UsersInput extends BusCollectionGenerator{
	private Scanner scanner;

	public UsersInput(Length length) {
        super(length);
	}

	{
		scanner = new Scanner(System.in);
	}

	private String inputNumber() {
		System.out.println("Input the bus number");
		String number = scanner.next();
		validator.validateNumber(number);
		return number;
	}
	
	private String inputModel() {
		System.out.println("Input the bus model");
		String model = scanner.next();
		validator.validateModel(model);
		return model;
	}

	private Float inputMileage() {
		System.out.println("Input the bus mileage");
		Float mileage = scanner.nextFloat();
		validator.validateMileage(mileage);
		return mileage;
	}

	private BusContract createBus() {
		BusContract item = new BusBuilder().setNumber(inputNumber())
				.setModel(inputModel())
				.setMileage(inputMileage()).build();
		System.out.println("Bus created");
		return item;
	}

	public Collection<BusContract> fillCollection() {
		Collection<BusContract> buses = Stream
				.generate(()->createBus())
				.limit(length.getValue())
				.collect(Collectors.toList());
		System.out.println("Collection filled");
		return buses;
	}
}
