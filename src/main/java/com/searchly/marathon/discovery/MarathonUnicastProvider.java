package com.searchly.marathon.discovery;

import mesosphere.marathon.client.Marathon;
import mesosphere.marathon.client.MarathonClient;
import mesosphere.marathon.client.model.v2.GetAppResponse;
import mesosphere.marathon.client.model.v2.Task;
import org.elasticsearch.Version;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.discovery.zen.ping.unicast.UnicastHostsProvider;

import java.util.List;

/**
 * @author ferhat
 */
public class MarathonUnicastProvider extends AbstractComponent implements UnicastHostsProvider {

    private Marathon marathon;

    public MarathonUnicastProvider(Settings settings) {
        super(settings);
        marathon = MarathonClient.getInstance(settings.get("marathon.host"));
    }

    @Override
    public List<DiscoveryNode> buildDynamicNodes() {
        List<DiscoveryNode> discoNodes = Lists.newArrayList();

        String clusterName = settings.get("cluster.name");
        GetAppResponse appGet = marathon.getApp(clusterName);

        for (Task task : appGet.getApp().getTasks()) {
            Integer[] ports = new Integer[1];
            task.getPorts().toArray(ports);
            discoNodes.add(new DiscoveryNode(task.getId(), new InetSocketTransportAddress(task.getHost(), ports[1]), Version.CURRENT));
        }

        return discoNodes;
    }
}
