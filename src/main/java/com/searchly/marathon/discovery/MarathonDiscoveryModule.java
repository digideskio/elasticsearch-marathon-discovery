package com.searchly.marathon.discovery;

import com.searchly.marathon.discovery.MarathonDiscovery;
import org.elasticsearch.discovery.Discovery;
import org.elasticsearch.discovery.zen.ZenDiscoveryModule;

public class MarathonDiscoveryModule extends ZenDiscoveryModule {

    @Override
    protected void bindDiscovery() {
        bind(Discovery.class).to(MarathonDiscovery.class).asEagerSingleton();
    }
}
