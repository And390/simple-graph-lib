package graph;

import java.util.*;


public interface Graph<V>
{
    void addVertex(V vertex);
    void addEdge(V a, V b);
    List<V> getPath(V a, V b);
}
