package dev.bluemethyst.mods.kuberest.net;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.kubejs.util.JsonIO;
import dev.latvian.mods.kubejs.typings.Info;
import java.io.IOException;
import java.io.OutputStream;

public class KubeRESTRequest {
    private final HttpExchange exchange;
    public String wildcard;
    public KubeRESTRequest(HttpExchange exchange, String wildcard) {
        this.exchange = exchange;
        this.wildcard = wildcard;
    }

    @Info(value = """
            Respond to the request with JSON data.
            @param {object} data - The JSON to respond with.""")
    public void respondJson(JsonObject jsonObject, int statusCode) throws IOException {
        String jsonString = JsonIO.toString(jsonObject);
        ConsoleJS.SERVER.info("A JSON Page was accessed.");
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, jsonString.length());
        OutputStream os = exchange.getResponseBody();
        os.write(jsonString.getBytes());
        os.close();
    }

    @Info(value = """
            Respond to the request with HTML data.
            @param {string} data - The HTML to respond with""")
    public void respondHtml(String html, int statusCode) throws IOException {
        ConsoleJS.SERVER.info("An HTML page was accessed.");
        exchange.getResponseHeaders().set("Content-Type", "text/html");
        exchange.sendResponseHeaders(statusCode, html.length());
        OutputStream os = exchange.getResponseBody();
        os.write(html.getBytes());
        os.close();
    }
}