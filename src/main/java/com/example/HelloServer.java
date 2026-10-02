import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class HelloServer {

    public static void main(String[] args) throws IOException {
        // Create HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Define what happens when someone visits "/"
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = """
                    <h1>Hello DevOps!</h1>
                    <p>You successfully ran a pure Java application</p>
                    <p>No Spring Boot • Only built-in Java libraries</p>
                    """;

                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, response.getBytes().length);

                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        server.setExecutor(null); // Use default executor
        server.start();

        System.out.println("Server started successfully on port 8080");
        System.out.println("Open http://localhost:8080 in your browser");
    }
}
