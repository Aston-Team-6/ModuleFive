package org.aston.module.presentation.view;

import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.aston.module.application.actions.SorterDataBusAction;
import org.aston.module.application.dto.OutputBus;
import org.aston.module.application.ports.BusSorterable;
import org.aston.module.application.ports.BusStorageable;
import org.aston.module.domain.ports.BusContract;
import org.aston.module.domain.values.collections.CustomList;
import org.aston.module.domain.values.enums.SorterType;
import org.aston.module.domain.values.enums.StorageType;
import org.aston.module.domain.values.objects.Length;
import org.aston.module.infrastructure.factories.SorterFactory;
import org.aston.module.infrastructure.factories.StorageFactory;
import org.aston.module.infrastructure.writers.BusFileWriter;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final SorterFactory sorterFactory;
    private final StorageFactory storageFactory;
    private final BusFileWriter fileWriter;

    public Menu(SorterFactory sorterFactory, StorageFactory storageFactory, BusFileWriter fileWriter) {
        this.sorterFactory = sorterFactory;
        this.storageFactory = storageFactory;
        this.fileWriter = fileWriter;
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Меню:");
        System.out.println("Начать выполнение: 1");
        System.out.println("Закончить выполнение: 2");
    }

    public void start() {
        boolean run = true;
        while (run) {
            printMenu();
            int swt = ReadInt("Ваш выбор: ");
            switch (swt) {
                case 1 -> runSort();
                case 2 -> {
                    System.out.println("Выход из программы.");
                    run = false;
                }
                default -> System.out.println("Было выбрано не то число");
            }
        }
        scanner.close();
    }

    public void runSort() {
        try {
            StorageType typeStorage = chooseStorageType();
            Length len = readLen();

            BusStorageable busStorageable;
            if (typeStorage == StorageType.FILE) {
                System.out.print("Введите путь до JSON-файла: ");
                String path = scanner.nextLine().trim();
                busStorageable = storageFactory.create(typeStorage, len, path);
            } else {
                busStorageable = storageFactory.create(typeStorage, len);
            }

            SorterType typeSorter = chooseSorterType();
            BusSorterable sortable = sorterFactory.create(typeSorter);

            SorterDataBusAction command = new SorterDataBusAction(busStorageable, sortable);
            OutputBus result = command.execute();

            printResult(result);
            offerSaveToFile(result);
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка данных: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Непредвиденная ошибка: " + e.getMessage());
        }
    }

    private Length readLen() {
        while (true) {
            int value = ReadInt("Введите размер коллекции: ");
            try {
                return new Length(value);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private int ReadInt(String text) {
        while (true) {
            System.out.print(text);
            String intStr = scanner.nextLine();
            try {
                return Integer.parseInt(intStr);
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private StorageType chooseStorageType() {
        System.out.println();
        System.out.println("JSON - 1");
        System.out.println("Ручной ввод - 2");
        System.out.println("Рандом - 3");

        while (true) {
            int swt = ReadInt("Выберите источник: ");
            switch (swt) {
                case 1 -> { return StorageType.FILE; }
                case 2 -> { return StorageType.INPUT; }
                case 3 -> { return StorageType.RANDOM; }
                default -> System.out.println("Была выбрана цифра не из меню.");
            }
        }
    }

    private SorterType chooseSorterType() {
        System.out.println();
        System.out.println("Модель - 1");
        System.out.println("Номер - 2");
        System.out.println("Пробег - 3");
        System.out.println("Пробег (доп задание) - 4");

        while (true) {
            int swt = ReadInt("Выберите сортировку: ");
            switch (swt) {
                case 1 -> { return SorterType.MODULE; }
                case 2 -> { return SorterType.NUMBER; }
                case 3 -> { return SorterType.MILEAGE; }
                case 4 -> { return SorterType.MILEAGE_ADDITIONAL; }
                default -> System.out.println("Была выбрана цифра не из меню.");
            }
        }
    }

    private void printResult(OutputBus result) {
        System.out.println();
        System.out.println("Результат:");
        System.out.println("Количество: " + result.busCount);
        System.out.println();
        if (result.busCollection == null || result.busCollection.isEmpty()) {
            System.out.println("(коллекция пуста)");
        } else {
            for (BusContract bus : result.busCollection) {
                System.out.println(bus);
            }
        }
        System.out.println();
    }

    private void offerSaveToFile(OutputBus result) {
        if (result.busCollection == null || result.busCollection.isEmpty()) {
            return;
        }

        int choice = ReadInt("Сохранить результат в файл? 1 - да, 2 - нет: ");
        scanner.nextLine();
        if (choice != 1) {
            return;
        }

        System.out.print("Введите путь до файла: ");
        String path = scanner.nextLine().trim();

        int sourceSize = result.busCollection.size();

        CustomList<BusContract> customList = result.busCollection.stream()
                .collect(Collectors.toCollection(() -> new CustomList<BusContract>()));

        if (sourceSize == 1) {
            BusContract only = result.busCollection.iterator().next();
            customList.add(only);
            customList.remove(only);
        }

        fileWriter.writeCollection(customList, path);
        System.out.println("Результат сохранён в файл: " + path);
    }
}