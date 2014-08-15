package com.searchly.service;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.common.component.AbstractLifecycleComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;

public class ElasticsearchEtcdDiscoveryService extends AbstractLifecycleComponent<ElasticsearchEtcdDiscoveryService> {

    @Inject
    public ElasticsearchEtcdDiscoveryService(final Settings settings) {
        super(settings);
        logger.info("CREATE ElasticsearchEtcdDiscoveryService");

        // TODO Your code..
    }

    @Override
    protected void doStart() throws ElasticsearchException {
        logger.info("START ElasticsearchEtcdDiscoveryService");

        // TODO Your code..
    }

    @Override
    protected void doStop() throws ElasticsearchException {
        logger.info("STOP ElasticsearchEtcdDiscoveryService");

        // TODO Your code..
    }

    @Override
    protected void doClose() throws ElasticsearchException {
        logger.info("CLOSE ElasticsearchEtcdDiscoveryService");

        // TODO Your code..
    }

}
