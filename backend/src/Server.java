import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {

    public static void start(int port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/api/report", exchange -> {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Content-Type", "application/json");

            try {
                Map<String, String> params = parseQuery(exchange.getRequestURI());
                String limitParam = params.get("limit");

                if (limitParam == null) {
                    sendJson(exchange, 400, "{\"error\":\"Missing required query param: limit\"}");
                    return;
                }

                int limit;
                try {
                    limit = Integer.parseInt(limitParam);
                } catch (NumberFormatException e) {
                    sendJson(exchange, 400, "{\"error\":\"limit must be an integer\"}");
                    return;
                }

                NumericalEngine.Report report = NumericalEngine.generateReport(limit);
                sendJson(exchange, 200, toJson(report));

            } catch (IllegalArgumentException e) {
                sendJson(exchange, 400, "{\"error\":\"" + escape(e.getMessage()) + "\"}");
            } catch (Exception e) {
                sendJson(exchange, 500, "{\"error\":\"Internal server error\"}");
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Server running at http://localhost:" + port + "/api/report?limit=20");
    }

    private static Map<String, String> parseQuery(URI uri) {
        Map<String, String> result = new HashMap<>();
        String query = uri.getQuery();
        if (query == null) return result;
        for (String pair : query.split("&")) {
            String[] kv = pair.split("=", 2);
            if (kv.length == 2) result.put(kv[0], kv[1]);
        }
        return result;
    }

    private static String toJson(NumericalEngine.Report report) {
        List<String> seq = report.sequence;
        StringBuilder seqJson = new StringBuilder("[");
        for (int i = 0; i < seq.size(); i++) {
            seqJson.append("\"").append(seq.get(i)).append("\"");
            if (i < seq.size() - 1) seqJson.append(",");
        }
        seqJson.append("]");

        return "{"
            + "\"total\":" + report.total + ","
            + "\"fizzCount\":" + report.fizzCount + ","
            + "\"buzzCount\":" + report.buzzCount + ","
            + "\"whizzCount\":" + report.whizzCount + ","
            + "\"sequence\":" + seqJson
            + "}";
    }

    private static String escape(String s) {
        return s.replace("\"", "'");
    }

    private static void sendJson(HttpExchange exchange, int status, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}