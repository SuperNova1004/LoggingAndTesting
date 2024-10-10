import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {

    @Test
    void constructor_NeedThrowException_WhenHorsesIsNull() {
        Executable executable = () -> new Hippodrome(null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructor_NeedThrowException_WhenHorsesIsEmpty() {
        Executable executable = () -> new Hippodrome(new ArrayList<>());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses_ReturnSameListAsConstructor() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse " + i, 10 + i));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> retrievedHorses = hippodrome.getHorses();

        assertEquals(horses.size(), retrievedHorses.size());
        for (int i = 0; i < horses.size(); i++) {
            assertSame(horses.get(i), retrievedHorses.get(i));
        }
    }

    @Test
    void move_CallsMoveOnAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinner_WithMaxDistance() {
        Horse horse1 = new Horse("Horse 1", 10, 50);
        Horse horse2 = new Horse("Horse 2", 12, 75);
        Horse horse3 = new Horse("Horse 3", 8, 60);

        List<Horse> horses = List.of(horse1, horse2, horse3);
        Hippodrome hippodrome = new Hippodrome(horses);

        Horse winner = hippodrome.getWinner();
        assertSame(horse2, winner);
    }
}
