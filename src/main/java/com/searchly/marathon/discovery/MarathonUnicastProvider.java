package com.searchly.marathon.discovery;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.SpecialPermission;
import org.elasticsearch.Version;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.discovery.zen.ping.unicast.UnicastHostsProvider;

import java.net.InetSocketAddress;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ferhat
 */
public class MarathonUnicastProvider extends AbstractComponent implements UnicastHostsProvider {

    private CloseableHttpClient httpclient;

    @Inject
    public MarathonUnicastProvider(Settings settings) {
        super(settings);

        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new SpecialPermission());
        }

        httpclient = HttpClients.createDefault();
    }

    @Override
    public List<DiscoveryNode> buildDynamicNodes() {
        return getNodesFromMarathonAPI();
    }

    private List<DiscoveryNode> getNodesFromMarathonAPI() {

        String marathonConnectionUrl = settings.get("marathon.host", "http://marathon.mesos:8080");

        List<DiscoveryNode> discoNodes = new ArrayList<>();

        AccessController.doPrivileged(
                (PrivilegedAction<Boolean>) () -> {
                    try {
                        // set port index to be used
                        Integer portOrder = Integer.parseInt(settings.get("marathon.port_index", "1"));

                        // get app details from marathon
                        String connectionUrl = marathonConnectionUrl + "/v2/apps/" + settings.get("marathon.task");

                        logger.info("Getting task details with url {}", connectionUrl);

                        HttpGet httpget = new HttpGet(connectionUrl);
                        String response = EntityUtils.toString(httpclient.execute(httpget).getEntity());

                        logger.info("Got response from marathon master '{}...'", response.substring(0, 30));

                        // parse json
                        ObjectMapper mapper = new ObjectMapper();
                        TypeReference<HashMap<String, Object>> typeRef
                                = new TypeReference<HashMap<String, Object>>() {
                        };
                        Map<String, Object> marathonResponse = mapper.readValue(response, typeRef);
                        Map app = (Map) marathonResponse.get("app");
                        List<Map<String,Object>> tasks = (List<Map<String,Object>>) app.get("tasks");

                        // create nodes to connect except self
                        discoNodes.addAll(tasks.stream().filter(task -> !settings.get("node.name").equals(task.get("id").toString())).map(task ->
                                new DiscoveryNode(task.get("id").toString(), new InetSocketTransportAddress
                                (new InetSocketAddress(task.get("host").toString(), ((List<Integer>)
                                        task.get("ports")).get(portOrder))), Version.CURRENT)).collect(Collectors.toList()));
                    } catch (Exception e) {
                        logger.error("Marathon discovery failure {}", e);
                        return Boolean.FALSE;
                    }
                    return Boolean.TRUE;
                }
        );
        return discoNodes;
    }
}
