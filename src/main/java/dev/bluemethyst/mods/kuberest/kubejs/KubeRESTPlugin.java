package dev.bluemethyst.mods.kuberest.kubejs;

import dev.bluemethyst.mods.kuberest.bindings.KubeRESTBindings;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;

public class KubeRESTPlugin extends KubeJSPlugin {
    /* Basic example of a KubeJS Plugin.
       To register your own plugins, add this class and package name to "kubejs.plugins.txt" in your Resources directory.
    */

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("KubeREST", KubeRESTBindings.class);
    }
}

