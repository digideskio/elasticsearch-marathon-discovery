package com.searchly.module;

import com.searchly.service.ElasticsearchEtcdDiscoveryService;
import org.elasticsearch.common.inject.AbstractModule;

public class ElasticsearchEtcdDiscoveryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ElasticsearchEtcdDiscoveryService.class).asEagerSingleton();
    }
}