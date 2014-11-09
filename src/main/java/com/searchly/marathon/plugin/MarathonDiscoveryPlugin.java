package com.searchly.marathon.plugin;

import org.elasticsearch.plugins.AbstractPlugin;

public class MarathonDiscoveryPlugin extends AbstractPlugin {
    @Override
    public String name() {
        return "marathon-discovery";
    }

    @Override
    public String description() {
        return "This is elasticsearch-marathon-discovery plugin.";
    }
}
