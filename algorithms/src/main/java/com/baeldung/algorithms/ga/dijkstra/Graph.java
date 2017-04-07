<<<<<<< HEAD:algorithms/src/main/java/com/baeldung/algorithms/dijkstra/Graph.java
package com.baeldung.algorithms.dijkstra;
=======
package com.baeldung.algorithms.ga.dijkstra;
>>>>>>> 22dda8c2be6861c56a47861137fbbbb067a72b0e:algorithms/src/main/java/com/baeldung/algorithms/ga/dijkstra/Graph.java

import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
}
