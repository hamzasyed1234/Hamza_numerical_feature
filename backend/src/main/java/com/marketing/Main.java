package com.marketing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        NumericalEngine engine = new NumericalEngine();

        int limit = 20; // default demo value
        if (args.length > 0) {
            try {
                limit = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument must be an integer. Using default: 20");
            }
        }

        try {
            List<String> items   = engine.generate(limit);
            ReportSummary summary = engine.summarize(items);

            System.out.println("Generated sequence (1 to " + limit + "):");
            System.out.println(items);
            System.out.println();
            System.out.println("--- Summary ---");
            System.out.println(summary);

        } catch (IllegalArgumentException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}
