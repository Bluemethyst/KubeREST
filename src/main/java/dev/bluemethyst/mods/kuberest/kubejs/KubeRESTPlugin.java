package dev.bluemethyst.mods.kuberest.kubejs;

import dev.bluemethyst.mods.kuberest.bindings.KubeRESTBindings;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;

public class KubeRESTPlugin extends KubeJSPlugin {
    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("KubeREST", KubeRESTBindings.class);
    }
}

