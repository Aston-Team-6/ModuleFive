package org.aston.module;

import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aston.module.yakov.interfaces.IBus;

public class UsersInput implements Input {
	private Scanner scanner;
	private int length;
	
	{
		scanner = new Scanner(System.in);
	}
	
	public int getLength() {
		return length;
	}
	
	private String inputNumber() {
		System.out.println("Input the bus number");
		String number = scanner.next();
		//validate data...
		return number;
	}
	
	private String inputModel() {
		System.out.println("Input the bus model");
		String model = scanner.next();
		//validate data...
		return model;
		
	}
	private float inputMileage() {
		System.out.println("Input the bus mileage");
		float mileage = scanner.nextFloat();
		//validate data...
		return mileage;
	}
	
	@Override
	public IBus createBus(String... info) {
		String number = inputNumber();
		String model = inputModel();
		float mileage = inputMileage();
		IBus item = new Bus(number, model, mileage);
		System.out.println("Bus created");
		return item;
	}
	
	@Override
	public Collection<IBus> fillCollection(int length) {
		this.length = length;
		Collection<IBus> buses = Stream
				.generate(()->createBus(null))
				.limit(length)
				.collect(Collectors.toList());
		System.out.println("Collection filled");
		return buses;
	}

}
