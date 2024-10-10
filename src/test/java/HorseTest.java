import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HorseTest {

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
            Horse horse = new Horse("Horse", 10.0, 5.0);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    public void move_NeedUpdateDistanceCorrectly() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Horse", 10.0, 5.0);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            assertEquals(10.0, horse.getDistance());  // 5.0 + 10.0 * 0.5 = 10.0
        }
    }
}
