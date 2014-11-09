elasticsearch-marathon-discovery
================================

Marathon API Based Unicast Disovery

Required paramaters;

* marathon.enabled: true
* discovery:
      type: com.searchly.marathon.module.MarathonDiscoveryModule
* marathon.host: "localhost:8080"
* discovery.zen.ping.multicast.enabled: false


* bin/plugin -url https://github.com/searchly/elasticsearch-marathon-discovery/releases/download/elasticsearch-marathon-discovery-0.0.1/elasticsearch-marathon-discovery-0.0.1.zip install marathon


