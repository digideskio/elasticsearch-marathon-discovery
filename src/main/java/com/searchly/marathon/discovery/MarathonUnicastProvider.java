package com.searchly.marathon.discovery;

import mesosphere.marathon.client.Marathon;
import mesosphere.marathon.client.MarathonClient;
import mesosphere.marathon.client.model.v2.GetAppResponse;
import mesosphere.marathon.client.model.v2.Task;
import mesosphere.marathon.client.utils.MarathonException;
import org.elasticsearch.Version;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.discovery.zen.ping.unicast.UnicastHostsProvider;

import java.util.List;

/**
 * @author ferhat
 */
public class MarathonUnicastProvider extends AbstractComponent implements UnicastHostsProvider {

    private Marathon marathon;

    @Inject
    public MarathonUnicastProvider(Settings settings) {
        super(settings);
        String marathonConnectionUrl = settings.get("marathon.host");
        marathon = MarathonClient.getInstance(marathonConnectionUrl);
    }

    @Override
    public List<DiscoveryNode> buildDynamicNodes() {
        List<DiscoveryNode> discoNodes = Lists.newArrayList();

        String clusterName = settings.get("cluster.name");
        Integer portOrder = Integer.parseInt(settings.get("marathon.port_index", "1"));
        GetAppResponse appGet;
        try {
            appGet = marathon.getApp(clusterName);
            for (Task task : appGet.getApp().getTasks()) {
                discoNodes.add(new DiscoveryNode(task.getId(), new InetSocketTransportAddress(task.getHost(),
                        ((List<Integer>) task.getPorts()).get(portOrder)), Version.CURRENT));
            }
        } catch (MarathonException e) {
            logger.error("Can not find marathon application via id {}", e, clusterName);
        }
        return discoNodes;
    }
}
