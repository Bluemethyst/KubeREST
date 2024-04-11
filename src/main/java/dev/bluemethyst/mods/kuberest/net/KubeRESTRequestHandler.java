package dev.bluemethyst.mods.kuberest.net;

import java.io.IOException;

public interface KubeRESTRequestHandler {

    void handle(KubeRESTRequest request) throws IOException;
}