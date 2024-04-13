package dev.bluemethyst.mods.kuberest.net;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KubeRESTEvent {
    private final HttpServer server;

    public KubeRESTEvent(HttpServer server) {
        this.server = server;
    }

    @Info(value = """
            Register a new GET request handler.
            @param {string} path - The URL path.
            @param {function} handler - The handler.""")
    public void onGet(String path, KubeRESTRequestHandler handler) {
        if (path.contains("*")) {
            String[] splitPath = path.split("\\*", 2);
            String contextPath = splitPath[0];
            String regexPath = path.replace("*", "([^/]*)");
            Pattern pattern = Pattern.compile(regexPath);
            server.createContext(contextPath, new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    String requestPath = exchange.getRequestURI().getPath();
                    Matcher matcher = pattern.matcher(requestPath);
                    if (matcher.matches()) {
                        String wildcardValue = matcher.group(1);
                        ConsoleJS.SERVER.log("Wildcard value: " + wildcardValue);
                        handler.handle(new KubeRESTRequest(exchange, wildcardValue));
                    }
                }
            });
        } else {
            server.createContext(path, new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    handler.handle(new KubeRESTRequest(exchange, null));
                }
            });
        }
    }
}