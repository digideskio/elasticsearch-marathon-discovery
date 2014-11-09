elasticsearch-marathon-discovery
================================

Marathon API Based Unicast Disovery

Required paramaters;

* marathon.enabled: true
* discovery.type: com.searchly.marathon.module.MarathonDiscoveryModule
* marathon.host: localhost
* marathon.port: 8080
* discovery.zen.ping.multicast.enabled: false


* bin/plugin -url https://github.com/searchly/elasticsearch-marathon-discovery/releases/download/elasticsearch-marathon-discovery-0.0.2/elasticsearch-marathon-discovery-0.0.2.zip install marathon


