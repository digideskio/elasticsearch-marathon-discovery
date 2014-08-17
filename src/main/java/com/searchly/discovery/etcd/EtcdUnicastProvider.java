package com.searchly.discovery.etcd;

import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.discovery.zen.ping.unicast.UnicastHostsProvider;

import java.util.List;

/**
 * @author ferhat
 */
public class EtcdUnicastProvider extends AbstractComponent implements UnicastHostsProvider {

    public EtcdUnicastProvider(Settings settings) {
        super(settings);
    }

    @Override
    public List<DiscoveryNode> buildDynamicNodes() {
        List<DiscoveryNode> discoNodes = Lists.newArrayList();
        return discoNodes;
    }
}
