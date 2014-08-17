package com.searchly.service;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.common.component.AbstractLifecycleComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;

public class EtcdDiscoveryService extends AbstractLifecycleComponent<EtcdDiscoveryService> {

    @Inject
    public EtcdDiscoveryService(final Settings settings) {
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
