# Выполненные задачи от Яков Рочева

1. Сформировать архитектуру приложения.
- Добавить класс Автобус (``Bus``).

2. (``SorterDataBusCommand``) Класс - команда, которая выполняет бизнес - задачу.
- Получение данных из Storage.
- Сортировка данных.


## Работа с ``SorterDataBusCommand``

Необхдимо реализовать несколько интерфейсов.

### 1. Интерфейс ``IBusStorageFactory``.

Интерфейс ``IBusStorageFactory`` - это фабрика, которая должна отдавать ``IBusStorage``. На основе класса enum ``StorageTypeEnum`` определяет какую реализацию интерфейса ``IBusStorage`` необходимо отдать. 

```java
import org.aston.module.yakov.interfaces.factories.IBusStorageFactory;
import org.aston.module.yakov.StorageTypeEnum;
import org.aston.module.yakov.interfaces.IBusStorage;

public class BusStorageFactory implements IBusStorageFactory {
    public IBusStorage create(StorageTypeEnum storageType) {
        switch (storageType) {
            case StorageTypeEnum.FILE:
                return new IBusStorage() { /* Реализовать интерфейс */}
        }
    }
}
```

### 2. Интерфейс ``IBusSorterFactory``.

Как и ``IBusStorageFactory`` интерфейс ``IBusSorterFactory`` работает аналогично. Это фабрика которая создает объект, имплементирующий ``IBusSorter``. 

```java

import org.aston.module.yakov.interfaces.factories.IBusSorterFactory;
import org.aston.module.yakov.SorterTypeEnum;
import org.aston.module.yakov.interfaces.IBusSorter;

public class BusSorterFactory implements IBusSorterFactory {
    public IBusSorter create(SorterTypeEnum sorterType) {
        switch (sorterType) {
            case SorterTypeEnum.NUMBER:
                return new IBusSorter() { /* Реализовать интерфейс */}
        }
    }
}
```

### Запуск ``SorterDataBusCommand``

```java

class Main {
    public static void main(Stringp[] args) {
        var sorterDataBusCommand = new SorterDataBusCommand(
            new BusStorageFactory(),
            new BusSorterFactory()
        );
    }
}

```