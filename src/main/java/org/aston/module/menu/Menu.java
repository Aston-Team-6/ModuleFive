package org.aston.module.menu;

import org.aston.module.actions.SorterDataBusAction;
import org.aston.module.controller.generators.FromJSONReader;
import org.aston.module.controller.generators.RandomGenerator;
import org.aston.module.controller.generators.UsersInput;
import org.aston.module.controller.sort.BusMileageSorter;
import org.aston.module.controller.sort.BusModelSorter;
import org.aston.module.controller.sort.BusNumberSorter;
import org.aston.module.dto.OutputBus;
import org.aston.module.interfaces.BusContract;
import org.aston.module.interfaces.BusSorterable;
import org.aston.module.interfaces.BusStorageable;
import org.aston.module.value.objects.Length;
import org.aston.module.values.enums.SorterType;
import org.aston.module.values.enums.StorageType;

import java.io.IOException;
import java.util.Scanner;

import org.aston.module.value.objects.Filename;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);

    public Menu() {}

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
            System.out.print("Ваш выбор: ");
            int swt = scanner.nextInt();
            scanner.nextLine();

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
            BusStorageable busStorageable = createStorage(typeStorage);

            if (busStorageable == null) {
                System.out.println("Не удалось создать источник данных.");
                return;
            }

            SorterType typeSorter = chooseSorterType();
            BusSorterable sortable = createSort(typeSorter);

            SorterDataBusAction command = new SorterDataBusAction(busStorageable, sortable);
            OutputBus result = command.execute();

            printResult(result);
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка данных: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Непредвиденная ошибка: " + e.getMessage());
        }
    }

    private BusSorterable createSort(SorterType type) {
        return switch (type) {
            case MODULE -> new BusModelSorter();
            case NUMBER -> new BusNumberSorter();
            case MILEAGE -> new BusMileageSorter();
            default -> throw new IllegalArgumentException("Неизвестный тип сортировки: " + type);
        };
    }

    private BusStorageable createStorage(StorageType type) {
        Length len = readLen();

        return switch (type) {
            case FILE -> {
                System.out.print("Введите путь до JSON-файла: ");
                String path = scanner.nextLine().trim();
                BusStorageable storage = null;
                try {
                    Filename filename = new Filename(path, "JSON");
                    storage = new FromJSONReader(filename, len);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                yield storage;
            }
            case INPUT -> new UsersInput(len);
            case RANDOM -> new RandomGenerator(len);
        };
    }

    private Length readLen() {
        while (true) {
            System.out.print("Введите размер коллекции: ");
            int value = scanner.nextInt();
            scanner.nextLine();
            try {
                return new Length(value);
            } catch (IllegalArgumentException ex) {
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
            System.out.print("Выберите источник: ");
            int swt = scanner.nextInt();
            scanner.nextLine();

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

        while (true) {
            System.out.print("Выберите сортировку: ");
            int swt = scanner.nextInt();
            scanner.nextLine();

            switch (swt) {
                case 1 -> { return SorterType.MODULE; }
                case 2 -> { return SorterType.NUMBER; }
                case 3 -> { return SorterType.MILEAGE; }
                default -> System.out.println("Была выбрана цифра не из меню.");
            }
        }
    }

    private void printResult(OutputBus result) {
        System.out.println();
        System.out.println("РЕЗУЛЬТАТ:");
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
}