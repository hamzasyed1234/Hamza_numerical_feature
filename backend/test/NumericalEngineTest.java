import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class NumericalEngineTest {

    // Helper: captures printed output from run()
    private String getOutput(int limit) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        NumericalEngine.run(limit);
        System.setOut(System.out);
        return out.toString();
    }

    // ── Rules ─────────────────────────────────────────────────────────────────

    @Test
    void multipleOfThree_printsFizz() {
        String output = getOutput(3);
        assertTrue(output.contains("Fizz"));
    }

    @Test
    void multipleOfFive_printsBuzz() {
        String output = getOutput(5);
        assertTrue(output.contains("Buzz"));
    }

    @Test
    void multipleOfSeven_printsWhizz() {
        String output = getOutput(7);
        assertTrue(output.contains("Whizz"));
    }

    @Test
    void multipleOfThreeAndFive_printsFizzBuzz() {
        String output = getOutput(15);
        assertTrue(output.contains("FizzBuzz"));
    }

    @Test
    void multipleOfThreeAndSeven_printsFizzWhizz() {
        String output = getOutput(21);
        assertTrue(output.contains("FizzWhizz"));
    }

    @Test
    void multipleOfFiveAndSeven_printsBuzzWhizz() {
        String output = getOutput(35);
        assertTrue(output.contains("BuzzWhizz"));
    }

    // Note: FizzBuzzWhizz (multiple of 3, 5, and 7) first occurs at 105,
    // which exceeds the max limit of 100, so it cannot be tested via run().

    @Test
    void plainNumber_printsNumber() {
        String output = getOutput(1);
        assertTrue(output.contains("1"));
    }

    // ── Report counters ───────────────────────────────────────────────────────

    @Test
    void report_fizzCountIsCorrect() {
        // run(15): Fizz at 3,6,9,12,15 = 5
        String output = getOutput(15);
        assertTrue(output.contains("Fizz  : 5"));
    }

    @Test
    void report_buzzCountIsCorrect() {
        // run(15): Buzz at 5,10,15 = 3
        String output = getOutput(15);
        assertTrue(output.contains("Buzz  : 3"));
    }

    @Test
    void report_whizzCountIsCorrect() {
        // run(15): Whizz at 7,14 = 2
        String output = getOutput(15);
        assertTrue(output.contains("Whizz : 2"));
    }

    @Test
    void report_totalIsCorrect() {
        String output = getOutput(20);
        assertTrue(output.contains("Total : 20"));
    }

    // ── Validation ────────────────────────────────────────────────────────────

    @Test
    void zero_throwsException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class, () -> NumericalEngine.run(0)
        );
        assertTrue(ex.getMessage().contains("greater than zero"));
    }

    @Test
    void negative_throwsException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class, () -> NumericalEngine.run(-5)
        );
        assertTrue(ex.getMessage().contains("greater than zero"));
    }

    @Test
    void overLimit_throwsException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class, () -> NumericalEngine.run(101)
        );
        assertTrue(ex.getMessage().contains("exceed 100"));
    }

    @Test
    void maxBoundary_succeeds() {
        assertDoesNotThrow(() -> NumericalEngine.run(100));
    }
}