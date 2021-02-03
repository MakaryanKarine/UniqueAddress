import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ConsoleTest {

    //unit tests for getters and setters . . .
    @Test
    void getSetReadFilePath() {
        Console.setReadFilePath("C:\\Users\\Karine\\IdeaProjects\\UniqueAddress\\src\\main\\resources\\DublicateAddressesTestFileSmall(10uniques).txt");
        assertEquals("C:\\Users\\Karine\\IdeaProjects\\UniqueAddress\\src\\main\\resources\\DublicateAddressesTestFileSmall(10uniques).txt", Console.getReadFilePath());
    }

    @Test
    void getSetWrongReadFilePath() {
        Console.setReadFilePath("C:\\Users\\Karine\\IdeaProjects\\UniqueAddress\\src\\main\\resources\\DublicateAddressesTestFileSmall(10uniques).txt");
        assertNotEquals("C:\\Users\\Karine\\IdeaProjects\\UniqueAddress\\src\\resources\\DublicateAddressesTestFileSmall(10uniques).txt", Console.getReadFilePath());
    }

    @Test
    void getsetUniqueAdrsCount() {
        Console.setUniqueAdrsCount(Long.valueOf(155));
        assertEquals(155, Console.getUniqueAdrsCount());
    }

    @Test
    void getsetWrongUniqueAdrsCount() {
        Console.setUniqueAdrsCount(Long.valueOf(18));
        assertNotEquals(180, Console.getUniqueAdrsCount());
    }

    // integration tests for final result
    @Test
    void checkConsoleInputReadFilePath() throws SQLException {
        Console.setReadFilePath("C:\\Users\\Karine\\IdeaProjects\\UniqueAddress\\src\\main\\resources\\DublicateAddressesTestFileSmall(10uniques).txt");
        String userInput = "C:\\Users\\Karine\\IdeaProjects\\UniqueAddress\\src\\main\\resources\\DublicateAddressesTestFileSmall(10uniques).txt";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        Console.console();
        assertEquals("C:\\Users\\Karine\\IdeaProjects\\UniqueAddress\\src\\main\\resources\\DublicateAddressesTestFileSmall(10uniques).txt", Console.getReadFilePath());
    }

    @Test
    void countUniqueItem() throws SQLException {
        Console.setReadFilePath("C:\\Users\\Karine\\IdeaProjects\\UniqueAddress\\src\\main\\resources\\DublicateAddressesTestFileSmall(10uniques).txt");
        String userInput = "C:\\Users\\Karine\\IdeaProjects\\UniqueAddress\\src\\main\\resources\\DublicateAddressesTestFileSmall(10uniques).txt";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        Console.console();
        assertEquals(10, Console.getUniqueAdrsCount());
    }

    @Test
    void wrongCountUniqueItem() throws SQLException {
        Console.setReadFilePath("C:\\Users\\Karine\\IdeaProjects\\UniqueAddress\\src\\main\\resources\\DublicateAddressesTestFileSmall(10uniques).txt");
        String userInput = "C:\\Users\\Karine\\IdeaProjects\\UniqueAddress\\src\\main\\resources\\DublicateAddressesTestFileSmall(10uniques).txt";
        InputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        Console.console();
        assertNotEquals(0, Console.getUniqueAdrsCount());
    }
}