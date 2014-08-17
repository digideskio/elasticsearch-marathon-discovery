package com.searchly.discovery.etcd;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.PluginsService;
import org.elasticsearch.test.ElasticsearchIntegrationTest;
import org.junit.Test;

import static org.elasticsearch.common.settings.ImmutableSettings.settingsBuilder;

/**
 * @author ferhat
 */
@EtcdAbstractTest.EtcdTest
@ElasticsearchIntegrationTest.ClusterScope(scope = ElasticsearchIntegrationTest.Scope.TEST, numDataNodes = 0, numClientNodes = 0, transportClientRatio = 0.0)
public class EtcdDiscoveryTest extends EtcdAbstractTest {

    @Test
    public void testStart() {
        Settings nodeSettings = settingsBuilder()
                .put("plugins." + PluginsService.LOAD_PLUGIN_FROM_CLASSPATH, true)
                .put("etcd.enabled", true)
                .put("discovery.type", "etcd")
                .build();
        internalCluster().startNode(nodeSettings);
    }
}
