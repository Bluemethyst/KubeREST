package dev.bluemethyst.mods.kuberest.net;

import com.sun.net.httpserver.HttpExchange;
import dev.latvian.mods.rhino.json.JsonParser;
import java.io.IOException;
import java.io.OutputStream;

import dev.latvian.mods.kubejs.typings.Info;
//import org.json.JSONObject;

public class KubeRESTRequest {
    private final HttpExchange exchange;

    public KubeRESTRequest(HttpExchange exchange) {
        this.exchange = exchange;
    }

    @Info(value = "Respond to the request.\n" +
            "@param {object} data - The data to respond with.")
    public void respond(String data) throws IOException {
        exchange.sendResponseHeaders(200, data.length());
        OutputStream os = exchange.getResponseBody();
        os.write(data.getBytes());
        os.close();
    }
}