public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--server")) {
            int port = 8080;
            if (args.length > 1) {
                try {
                    port = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid port, using default 8080");
                }
            }
            try {
                Server.start(port);
            } catch (Exception e) {
                System.err.println("Failed to start server: " + e.getMessage());
            }
            return;
        }

        int limit = 20; // default

        if (args.length > 0) {
            try {
                limit = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument must be an integer. Using default: 20");
            }
        }

        try {
            NumericalEngine.run(limit);
        } catch (IllegalArgumentException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}