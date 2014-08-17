package com.searchly.discovery.etcd;

import org.elasticsearch.Version;
import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.node.DiscoveryNodeService;
import org.elasticsearch.common.collect.ImmutableList;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.discovery.DiscoverySettings;
import org.elasticsearch.discovery.zen.ZenDiscovery;
import org.elasticsearch.discovery.zen.ping.ZenPing;
import org.elasticsearch.discovery.zen.ping.ZenPingService;
import org.elasticsearch.discovery.zen.ping.unicast.UnicastZenPing;
import org.elasticsearch.node.settings.NodeSettingsService;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

/**
 * @author ferhat
 */
public class EtcdDiscovery extends ZenDiscovery {

    @Inject
    public EtcdDiscovery(Settings settings, ClusterName clusterName, ThreadPool threadPool, TransportService transportService, ClusterService clusterService,
                         NodeSettingsService nodeSettingsService, DiscoveryNodeService discoveryNodeService,
                         ZenPingService pingService, Version version, DiscoverySettings discoverySettings) {
        super(settings, clusterName, threadPool, transportService, clusterService, nodeSettingsService, discoveryNodeService, pingService, version, discoverySettings);

        if (settings.getAsBoolean("etcd.enabled", true)) {
            ImmutableList<? extends ZenPing> zenPings = pingService.zenPings();
            UnicastZenPing unicastZenPing = null;
            for (ZenPing zenPing : zenPings) {
                if (zenPing instanceof UnicastZenPing) {
                    unicastZenPing = (UnicastZenPing) zenPing;
                    break;
                }
            }

            if (unicastZenPing != null) {
                unicastZenPing.addHostsProvider(new EtcdUnicastProvider(settings));
                pingService.zenPings(ImmutableList.of(unicastZenPing));
            } else {
                logger.warn("failed to apply ec2 unicast discovery, no unicast ping found");
            }
        }
    }
}
