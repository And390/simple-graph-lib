package graph;


public class UndirectedGraph<V> extends DirectedGraph<V>
{
    @Override
    public void addEdge(V a, V b) {
        super.addEdge(a, b);
        super.addEdge(b, a);
    }
}
