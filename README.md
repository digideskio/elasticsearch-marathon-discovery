elasticsearch-marathon-discovery
================================

Marathon API Based Unicast Disovery

Required paramaters;

* marathon.enabled: true
* discovery:
      type: com.searchly.marathon.module.MarathonDiscoveryModule
* marathon.host: "localhost:8080"
* discovery.zen.ping.multicast.enabled: false
