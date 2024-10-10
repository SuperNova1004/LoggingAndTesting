import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)

public class HorseTest {


    // более коротко назвать некоторые методы не получилось :с
    @Test
    public void constructor_NeedThrowException_WhenNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 10.0, 5.0);
        });
        assertEquals("Name cannot be null.", exception.getMessage());
    }


    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void constructor_NeedThrowException_WhenNameIsBlank(String blankName) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(blankName, 10.0, 5.0);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void constructor_NeedThrowException_WhenSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Horse", -10.0, 5.0);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void constructor_NeedThrowException_WhenDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Horse", 10.0, -5.0);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getNameTest() {
        Horse horse = new Horse("Horse", 10.0, 5.0);
        assertEquals("Horse", horse.getName());
    }

    @Test
    public void getSpeedTest() {
        Horse horse = new Horse("Horse", 10.0, 5.0);
        assertEquals(10.0, horse.getSpeed());
    }

    @Test
    public void getDistanceTest() {
        Horse horse = new Horse("Horse", 10.0, 5.0);
        assertEquals(5.0, horse.getDistance());
    }

    @Test
    public void getDistance_NeedToReturnZero() {
        Horse horse = new Horse("Horse", 10.0);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    public void move_Test() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            // Создаем экземпляр Horse
            Horse horse = new Horse("Horse", 10.0, 5.0);

            // Настраиваем поведение статического метода
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            // Вызываем метод move
            horse.move();

            // Проверяем, что статический метод был вызван
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

            // Проверяем, что distance изменился правильно
            assertEquals(5.0 + 10.0 * 0.5, horse.getDistance());
        }
    }

    @Test
    void move_UsesGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            // Настраиваем список лошадей
            List<Horse> horses = new ArrayList<>();
            horses.add(new Horse("Horse 1", 10));
            horses.add(new Horse("Horse 2", 12));
            horses.add(new Horse("Bucephalus", 2.4));
            horses.add(new Horse("Ace of Spades", 2.5));
            horses.add(new Horse("Zephyr", 2.6));
            horses.add(new Horse("Blaze", 2.7));
            horses.add(new Horse("Lobster", 2.8));
            horses.add(new Horse("Pegasus", 2.9));
            horses.add(new Horse("Cherry", 3));

            // Настраиваем поведение статического метода
            mockedStatic.when(() -> Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(0.5);

            // Создаем экземпляр Hippodrome
            Hippodrome hippodrome = new Hippodrome(horses);

            // Вызываем метод move
            hippodrome.move();

            // Проверяем, что distance изменился правильно для каждой лошади
            assertEquals(10 * 0.5, horses.get(0).getDistance());
            assertEquals(12 * 0.5, horses.get(1).getDistance());
        }
    }
}

