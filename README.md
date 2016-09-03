elasticsearch-marathon-discovery
================================

Marathon API Based Unicast Discovery

Required parameters;

* discovery.type: marathon
* marathon:
    enabled: true
    host: http://localhost:8080 # marathon master
    task: elasticsearch # marathon task id
    port_index: 1 # this property is the index value of ports defined while creating marathon container/docker

* discovery.zen.ping.multicast.enabled: false

* bin/plugin install https://github.com/searchly/elasticsearch-marathon-discovery/releases/download/elasticsearch-marathon-discovery-0.0.6/elasticsearch-marathon-discovery-0.0.6.zip
