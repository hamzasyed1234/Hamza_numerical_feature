package com.marketing;

import java.util.ArrayList;
import java.util.List;

public class NumericalEngine {

    /**
     * Generates a list of keyword-translated values from 1 to limit (inclusive).
     *
     * @param limit the upper bound of the sequence (must be > 0 and <= 100)
     * @return list of strings where numbers are replaced according to rules
     * @throws IllegalArgumentException if limit is <= 0 or > 100
     */
    public List<String> generate(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException(
                "Invalid input: number must be greater than zero. Received: " + limit
            );
        }
        if (limit > 100) {
            throw new IllegalArgumentException(
                "Invalid input: number must not exceed 100. Received: " + limit
            );
        }

        List<String> result = new ArrayList<>();

        for (int i = 1; i <= limit; i++) {
            result.add(translate(i));
        }

        return result;
    }

    /**
     * Translates a single number into its keyword representation.
     */
    public String translate(int number) {
        StringBuilder word = new StringBuilder();

        if (number % 3 == 0) word.append("Fizz");
        if (number % 5 == 0) word.append("Buzz");
        if (number % 7 == 0) word.append("Whizz");

        return word.length() > 0 ? word.toString() : String.valueOf(number);
    }

    /**
     * Builds a statistical summary from a generated list.
     */
    public ReportSummary summarize(List<String> items) {
        int fizzCount  = 0;
        int buzzCount  = 0;
        int whizzCount = 0;

        for (String item : items) {
            if (item.contains("Fizz"))  fizzCount++;
            if (item.contains("Buzz"))  buzzCount++;
            if (item.contains("Whizz")) whizzCount++;
        }

        return new ReportSummary(items.size(), fizzCount, buzzCount, whizzCount);
    }
}
