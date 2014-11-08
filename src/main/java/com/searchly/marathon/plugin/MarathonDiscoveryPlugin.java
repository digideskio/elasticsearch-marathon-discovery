package com.searchly.marathon.plugin;

import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.common.settings.Settings;
import com.searchly.marathon.module.MarathonDiscoveryModule;
import org.elasticsearch.plugins.AbstractPlugin;

import java.util.Collection;

public class MarathonDiscoveryPlugin extends AbstractPlugin {
    @Override
    public String name() {
        return "ElasticsearchMarathonDiscoveryPlugin";
    }

    @Override
    public String description() {
        return "This is a elasticsearch-marathon-discovery plugin.";
    }

    @Override
    public Collection<Module> modules(Settings settings) {
        Collection<Module> modules = Lists.newArrayList();
        if (settings.getAsBoolean("marathon.enabled", true)) {
            modules.add(new MarathonDiscoveryModule());
        }
        return modules;
    }
}
