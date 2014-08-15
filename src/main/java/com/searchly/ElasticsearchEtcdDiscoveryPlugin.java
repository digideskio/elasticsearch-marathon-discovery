package com.searchly;

import java.util.Collection;

import com.searchly.module.ElasticsearchEtcdDiscoveryModule;
import com.searchly.service.ElasticsearchEtcdDiscoveryService;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.component.LifecycleComponent;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.plugins.AbstractPlugin;
import org.elasticsearch.rest.RestModule;
import org.elasticsearch.river.RiversModule;

public class ElasticsearchEtcdDiscoveryPlugin extends AbstractPlugin {
    @Override
    public String name() {
        return "ElasticsearchEtcdDiscoveryPlugin";
    }

    @Override
    public String description() {
        return "This is a elasticsearch-etcd-discovery plugin.";
    }

    // for Service
    @Override
    public Collection<Class<? extends Module>> modules() {
        final Collection<Class<? extends Module>> modules = Lists
                .newArrayList();
        modules.add(ElasticsearchEtcdDiscoveryModule.class);
        return modules;
    }

    // for Service
    @SuppressWarnings("rawtypes")
    @Override
    public Collection<Class<? extends LifecycleComponent>> services() {
        final Collection<Class<? extends LifecycleComponent>> services = Lists
                .newArrayList();
        services.add(ElasticsearchEtcdDiscoveryService.class);
        return services;
    }
}
