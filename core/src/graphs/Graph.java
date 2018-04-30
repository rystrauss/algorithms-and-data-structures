package graphs;

import java.util.List;
import java.util.Map;

public abstract class Graph {

    private Map<Vertex, List<Edge>> incoming, outgoing;
    private int V, E;

    public Graph() {

    }

}
