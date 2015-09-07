elasticsearch-marathon-discovery
================================

Marathon API Based Unicast Disovery

Required paramaters;

* discovery.type: com.searchly.marathon.module.MarathonDiscoveryModule
* marathon
    enabled: true
    host: http://localhost:8080 # marathon master
    port_index: 2 # this property is the index value of ports defined while creating marathon container/docker

* discovery.zen.ping.multicast.enabled: false


* bin/plugin -url https://github.com/searchly/elasticsearch-marathon-discovery/releases/download/elasticsearch-marathon-discovery-0.0.4/elasticsearch-marathon-discovery-0.0.4.zip install marathon


