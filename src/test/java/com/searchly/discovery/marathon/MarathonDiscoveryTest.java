package com.searchly.discovery.marathon;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.PluginsService;
import org.elasticsearch.test.ElasticsearchIntegrationTest;
import org.junit.Test;

import static org.elasticsearch.common.settings.ImmutableSettings.settingsBuilder;

/**
 * @author ferhat
 */
@MarathonAbstractTest.MarathonTest
@ElasticsearchIntegrationTest.ClusterScope(scope = ElasticsearchIntegrationTest.Scope.TEST, numDataNodes = 0, numClientNodes = 0, transportClientRatio = 0.0)
public class MarathonDiscoveryTest extends MarathonAbstractTest {

    @Test
    public void testStart() {
        Settings nodeSettings = settingsBuilder()
                .put("plugins." + PluginsService.LOAD_PLUGIN_FROM_CLASSPATH, true)
                .put("marathon.enabled", true)
                .put("discovery.type", "marathon")
                .build();
        internalCluster().startNode(nodeSettings);
    }
}
