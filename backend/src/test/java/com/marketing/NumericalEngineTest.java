package com.marketing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumericalEngineTest {

    private NumericalEngine engine;

    @BeforeEach
    void setUp() {
        engine = new NumericalEngine();
    }

    // ── Translation rules ──────────────────────────────────────────────────────

    @Test
    void plainNumber_returnsNumberAsString() {
        assertEquals("1",  engine.translate(1));
        assertEquals("2",  engine.translate(2));
        assertEquals("11", engine.translate(11));
    }

    @Test
    void multipleOfThree_returnsFizz() {
        assertEquals("Fizz", engine.translate(3));
        assertEquals("Fizz", engine.translate(6));
        assertEquals("Fizz", engine.translate(9));
    }

    @Test
    void multipleOfFive_returnsBuzz() {
        assertEquals("Buzz", engine.translate(5));
        assertEquals("Buzz", engine.translate(10));
        assertEquals("Buzz", engine.translate(25));
    }

    @Test
    void multipleOfSeven_returnsWhizz() {
        assertEquals("Whizz", engine.translate(7));
        assertEquals("Whizz", engine.translate(14));
        assertEquals("Whizz", engine.translate(49));
    }

    @Test
    void multipleOfThreeAndFive_returnsFizzBuzz() {
        assertEquals("FizzBuzz", engine.translate(15));
        assertEquals("FizzBuzz", engine.translate(30));
    }

    @Test
    void multipleOfThreeAndSeven_returnsFizzWhizz() {
        assertEquals("FizzWhizz", engine.translate(21));
        assertEquals("FizzWhizz", engine.translate(63));
    }

    @Test
    void multipleOfFiveAndSeven_returnsBuzzWhizz() {
        assertEquals("BuzzWhizz", engine.translate(35));
        assertEquals("BuzzWhizz", engine.translate(70));
    }

    @Test
    void multipleOfThreeFiveAndSeven_returnsFizzBuzzWhizz() {
        assertEquals("FizzBuzzWhizz", engine.translate(105));
    }

    // ── Generate range ─────────────────────────────────────────────────────────

    @Test
    void generate_producesCorrectListSize() {
        List<String> result = engine.generate(10);
        assertEquals(10, result.size());
    }

    @Test
    void generate_firstItemIsOne() {
        List<String> result = engine.generate(5);
        assertEquals("1", result.get(0));
    }

    @Test
    void generate_correctSampleValues() {
        List<String> result = engine.generate(15);
        assertEquals("Fizz",     result.get(2));   // index 2 = number 3
        assertEquals("Buzz",     result.get(4));   // index 4 = number 5
        assertEquals("Whizz",    result.get(6));   // index 6 = number 7
        assertEquals("FizzBuzz", result.get(14));  // index 14 = number 15
    }

    @Test
    void generate_maxBoundary_succeeds() {
        List<String> result = engine.generate(100);
        assertEquals(100, result.size());
    }

    // ── Security / validation ──────────────────────────────────────────────────

    @Test
    void generate_zero_throwsException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class, () -> engine.generate(0)
        );
        assertTrue(ex.getMessage().contains("greater than zero"));
    }

    @Test
    void generate_negative_throwsException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class, () -> engine.generate(-5)
        );
        assertTrue(ex.getMessage().contains("greater than zero"));
    }

    @Test
    void generate_overLimit_throwsException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class, () -> engine.generate(101)
        );
        assertTrue(ex.getMessage().contains("exceed 100"));
    }

    // ── Summary statistics ─────────────────────────────────────────────────────

    @Test
    void summarize_countsCorrectly() {
        List<String> items = engine.generate(15);
        ReportSummary summary = engine.summarize(items);

        assertEquals(15, summary.getTotalItems());
        // Fizz appears at: 3,6,9,12,15 → 5 times
        assertEquals(5, summary.getFizzCount());
        // Buzz appears at: 5,10,15 → 3 times
        assertEquals(3, summary.getBuzzCount());
        // Whizz appears at: 7,14 → 2 times
        assertEquals(2, summary.getWhizzCount());
    }

    @Test
    void summarize_combosCountInEachCategory() {
        // 21 = FizzWhizz → should count as both Fizz and Whizz
        List<String> items = engine.generate(21);
        ReportSummary summary = engine.summarize(items);

        assertTrue(summary.getFizzCount() >= 1);
        assertTrue(summary.getWhizzCount() >= 1);
    }
}
