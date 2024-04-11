package dev.bluemethyst.mods.kuberest.net;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import dev.latvian.mods.kubejs.typings.Info;

import java.io.IOException;

public class KubeRESTEvent {
    private final HttpServer server;

    public KubeRESTEvent(HttpServer server) {
        this.server = server;
    }

    @Info(value = "Register a new GET request handler.\n" +
            "@param {string} path - The URL path.\n" +
            "@param {function} handler - The handler.")
    public void onGet(String path, KubeRESTRequestHandler handler) {
        server.createContext(path, new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                handler.handle(new KubeRESTRequest(exchange));
            }
        });
    }
}