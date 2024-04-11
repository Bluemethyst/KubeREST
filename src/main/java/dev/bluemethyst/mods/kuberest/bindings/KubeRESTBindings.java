package dev.bluemethyst.mods.kuberest.bindings;

import dev.bluemethyst.mods.kuberest.net.KubeRESTEvent;
import dev.bluemethyst.mods.kuberest.KubeREST;
import com.sun.net.httpserver.HttpServer;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.util.ConsoleJS;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public interface KubeRESTBindings {
    Map<Integer, HttpServer> servers = new HashMap<>();

    @Info(value = "Host a new WebServer.\n" +
            "@param {number} port - The port number.\n" +
            "@param {function} eventConsumer - The event consumer.")
    static void host(int port, Consumer<KubeRESTEvent> eventConsumer) {
        if (port == 25565 || port ==80 || port == 443) {
            KubeREST.LOGGER.error("Ports 25565, 80 and 443 are reserved for Minecraft and HTTP/HTTPS servers.");
            ConsoleJS.SERVER.error("Ports 25565, 80 and 443 are reserved for Minecraft and HTTP/HTTPS servers.");
            return;
        }
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.setExecutor(null);
            server.start();
            servers.put(port, server);
            eventConsumer.accept(new KubeRESTEvent(server));
            KubeREST.LOGGER.info("Server hosted on port: " + port);
            ConsoleJS.SERVER.info("Server hosted on port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Info(value = "Stop the server.\n" +
            "@param {number} port - The port number.")
    static void stop(int port) {
        HttpServer server = servers.get(port);
        if (server != null) {
            server.stop(0);
            servers.remove(port);
            ConsoleJS.SERVER.info("Server stopped on port: " + port);
            KubeREST.LOGGER.info("Server stopped on port: " + port);
        }
    }
}