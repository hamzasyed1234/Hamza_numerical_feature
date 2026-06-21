import java.util.ArrayList;
import java.util.List;

public class NumericalEngine {

    public static class Report {
        public final int total;
        public final int fizzCount;
        public final int buzzCount;
        public final int whizzCount;
        public final List<String> sequence;

        public Report(int total, int fizzCount, int buzzCount, int whizzCount, List<String> sequence) {
            this.total = total;
            this.fizzCount = fizzCount;
            this.buzzCount = buzzCount;
            this.whizzCount = whizzCount;
            this.sequence = sequence;
        }
    }

    /** Pure logic — no printing. Used by both the CLI and the HTTP server. */
    public static Report generateReport(int limit) {
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

        List<String> sequence = new ArrayList<>();
        int fizz = 0, buzz = 0, whizz = 0;

        for (int i = 1; i <= limit; i++) {
            String word = "";
            if (i % 3 == 0) word += "Fizz";
            if (i % 5 == 0) word += "Buzz";
            if (i % 7 == 0) word += "Whizz";
            if (word.isEmpty()) word = String.valueOf(i);

            sequence.add(word);

            if (word.contains("Fizz"))  fizz++;
            if (word.contains("Buzz"))  buzz++;
            if (word.contains("Whizz")) whizz++;
        }

        return new Report(limit, fizz, buzz, whizz, sequence);
    }

    /** CLI wrapper — same console output as before. */
    public static void run(int limit) {
        Report report = generateReport(limit);

        report.sequence.forEach(System.out::println);

        System.out.println("--- Report ---");
        System.out.println("Total : " + report.total);
        System.out.println("Fizz  : " + report.fizzCount);
        System.out.println("Buzz  : " + report.buzzCount);
        System.out.println("Whizz : " + report.whizzCount);
    }
}