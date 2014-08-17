package org.elasticsearch.discovery;

import com.searchly.discovery.etcd.EtcdDiscovery;
import com.searchly.service.EtcdDiscoveryService;
import org.elasticsearch.common.inject.AbstractModule;
import org.elasticsearch.discovery.zen.ZenDiscoveryModule;

public class EtcdDiscoveryModule extends ZenDiscoveryModule {

    @Override
    protected void bindDiscovery() {
        bind(Discovery.class).to(EtcdDiscovery.class).asEagerSingleton();
    }
}
