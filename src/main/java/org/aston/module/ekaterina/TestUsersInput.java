package org.aston.module;

import java.util.List;

import org.aston.module.yakov.interfaces.IBus;

public class TestUsersInput {

	public static void main(String[] args) {
		UsersInput fromKeyboard = new UsersInput();
		List<IBus> buses = (List<IBus>) fromKeyboard.fillCollection(3);
		for (int i = 0; i < fromKeyboard.getLength(); i++)
			System.out.println(buses.get(i).toString());


	}

}
