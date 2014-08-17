package com.searchly;

import java.util.Collection;

import org.elasticsearch.discovery.EtcdDiscoveryModule;
import com.searchly.service.EtcdDiscoveryService;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.component.LifecycleComponent;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.plugins.AbstractPlugin;

public class EtcdDiscoveryPlugin extends AbstractPlugin {
    @Override
    public String name() {
        return "ElasticsearchEtcdDiscoveryPlugin";
    }

    @Override
    public String description() {
        return "This is a elasticsearch-etcd-discovery plugin.";
    }

    // for Service
    @SuppressWarnings("rawtypes")
    @Override
    public Collection<Class<? extends LifecycleComponent>> services() {
        final Collection<Class<? extends LifecycleComponent>> services = Lists
                .newArrayList();
        services.add(EtcdDiscoveryService.class);
        return services;
    }
}
