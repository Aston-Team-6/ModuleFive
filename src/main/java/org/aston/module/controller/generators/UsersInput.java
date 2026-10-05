package org.aston.module.controller.generators;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aston.module.controller.builder.BusBuilder;
import org.aston.module.controller.validator.BusDataValidator;
import org.aston.module.dto.BusFromStorage;
import org.aston.module.interfaces.BusContract;
import org.aston.module.interfaces.BusStorageable;
import org.aston.module.interfaces.StorageDataTransferable;
import org.aston.module.value.objects.Length;

public class UsersInput implements BusStorageable {
	private Scanner scanner;
	private BusDataValidator validator;
	private Length length;
	public UsersInput(Length length)
	{
		this.length = length;
	}

	{
		scanner = new Scanner(System.in);
		validator = new BusDataValidator();
	}
	
	public Length getLength() {
		return length;
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

	@Override
	public StorageDataTransferable getData() throws IOException {
		return new BusFromStorage(fillCollection(), length.getValue());
	}
}
