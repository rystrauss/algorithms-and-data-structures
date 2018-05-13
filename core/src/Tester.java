import graphs.Graph;

public class Tester {

    public static void main(String[] args) {
        Graph g = new Graph();
        for (int i = 0; i < 10; i++) {
            g.addVertex(i);
        }
        g.addEdge(1, 5);
        g.addEdge(1, 7);
        g.addEdge(5, 3);
        g.addEdge(5, 8);
        g.addEdge(8, 1);
        g.addEdge(8, 6);
        System.out.println(g.dfs(1));
    }

}
