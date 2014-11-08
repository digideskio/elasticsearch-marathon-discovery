package com.searchly.marathon.discovery;

import org.elasticsearch.Version;
import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.node.DiscoveryNodeService;
import org.elasticsearch.common.collect.ImmutableList;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.discovery.DiscoverySettings;
import org.elasticsearch.discovery.zen.ZenDiscovery;
import org.elasticsearch.discovery.zen.elect.ElectMasterService;
import org.elasticsearch.discovery.zen.ping.ZenPing;
import org.elasticsearch.discovery.zen.ping.ZenPingService;
import org.elasticsearch.discovery.zen.ping.unicast.UnicastZenPing;
import org.elasticsearch.node.settings.NodeSettingsService;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

/**
 * @author ferhat
 */
public class MarathonDiscovery extends ZenDiscovery {

    @Inject
    public MarathonDiscovery(Settings settings, ClusterName clusterName, ThreadPool threadPool, TransportService transportService, ClusterService clusterService,
                             NodeSettingsService nodeSettingsService, DiscoveryNodeService discoveryNodeService,
                             ZenPingService pingService, DiscoverySettings discoverySettings, ElectMasterService electMasterService) {
        super(settings, clusterName, threadPool, transportService, clusterService, nodeSettingsService, discoveryNodeService, pingService, electMasterService, discoverySettings);

        ImmutableList<? extends ZenPing> zenPings = pingService.zenPings();
        UnicastZenPing unicastZenPing = null;
        for (ZenPing zenPing : zenPings) {
            if (zenPing instanceof UnicastZenPing) {
                unicastZenPing = (UnicastZenPing) zenPing;
                break;
            }
        }

        if (unicastZenPing != null) {
            unicastZenPing.addHostsProvider(new MarathonUnicastProvider(settings));
            pingService.zenPings(ImmutableList.of(unicastZenPing));
        } else {
            logger.warn("failed to apply marathon unicast discovery, no unicast ping found");
        }
    }
}
