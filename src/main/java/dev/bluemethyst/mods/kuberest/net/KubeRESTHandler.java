package dev.bluemethyst.mods.kuberest.net;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

public class KubeRESTHandler implements HttpHandler {
    private final String data;

    public KubeRESTHandler(String data) {
        this.data = data;
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        String response = this.data;
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
        System.out.println("Request processed");
    }
}