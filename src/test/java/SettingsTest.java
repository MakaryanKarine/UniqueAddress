import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SettingsTest {

    // unit tests for getters . . .
    @org.junit.jupiter.api.Test
    void getPass() {
        assertEquals("15L76K34h0m!45GpoN", Settings.getPass());
    }

    @org.junit.jupiter.api.Test
    void getUser() {
        assertEquals("root", Settings.getUser());
    }

    @org.junit.jupiter.api.Test
    void getHostURL() {
        assertEquals("jdbc:mysql://localhost:3306/addresses", Settings.getHostURL());
    }

    @org.junit.jupiter.api.Test
    void getWrongPass() {
        assertNotEquals("15L76K34h0m45GpoN", Settings.getPass());
    }

    @org.junit.jupiter.api.Test
    void getWrongUser() {
        assertNotEquals("rooot", Settings.getUser());
    }

    @org.junit.jupiter.api.Test
    void getWrongHostURL() {
        assertNotEquals("jdbc:mysql://localhost:3306/adresses", Settings.getHostURL());
    }
}