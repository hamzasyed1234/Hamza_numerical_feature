
public class NumericalEngine {

    public static void run(int limit) {
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

        int fizz = 0, buzz = 0, whizz = 0;

        for (int i = 1; i <= limit; i++) {
            String word = "";
            if (i % 3 == 0) word += "Fizz";
            if (i % 5 == 0) word += "Buzz";
            if (i % 7 == 0) word += "Whizz";
            if (word.isEmpty()) word = String.valueOf(i);

            System.out.println(word);

            if (word.contains("Fizz"))  fizz++;
            if (word.contains("Buzz"))  buzz++;
            if (word.contains("Whizz")) whizz++;
        }

        System.out.println("--- Report ---");
        System.out.println("Total : " + limit);
        System.out.println("Fizz  : " + fizz);
        System.out.println("Buzz  : " + buzz);
        System.out.println("Whizz : " + whizz);
    }
}