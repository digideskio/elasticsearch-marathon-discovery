package com.searchly.marathon.plugin;

import com.searchly.marathon.discovery.MarathonDiscovery;
import com.searchly.marathon.discovery.MarathonUnicastProvider;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.discovery.DiscoveryModule;
import org.elasticsearch.plugins.Plugin;

public class MarathonDiscoveryPlugin extends Plugin {

    private final Settings settings;

    public MarathonDiscoveryPlugin(Settings settings) {
        this.settings = settings;
    }

    @Override
    public String name() {
        return "marathon-discovery";
    }

    @Override
    public String description() {
        return "This is elasticsearch-marathon-discovery plugin.";
    }

    public void onModule(DiscoveryModule discoveryModule) {
        if (settings.getAsBoolean("marathon.enabled", false)) {
            discoveryModule.addDiscoveryType("marathon", MarathonDiscovery.class);
            discoveryModule.addUnicastHostProvider(MarathonUnicastProvider.class);
        }
    }
}