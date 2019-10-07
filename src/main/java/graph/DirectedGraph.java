package graph;

import java.util.*;


public class DirectedGraph<V> implements Graph<V>
{
    private HashMap<V, HashSet<V>> edges = new HashMap<>();

    public void addVertex(V vertex) {
        edges.computeIfAbsent(vertex, (v) -> new HashSet<>());
    }

    public void addEdge(V a, V b) {
        HashSet<V> aEdges = edges.get(a);
        if (aEdges == null) throw new NoSuchElementException("Graph doesn't contain vertex: "+a.toString());
        if (!edges.containsKey(b)) throw new NoSuchElementException("Graph doesn't contain vertex: "+b.toString());
        aEdges.add(b);
    }

    /**
     * Implements a breadth-first search to find the shortest path between `start` and `end` vertices.
     * If there are more than one shortest path the result will be one of them.
     * @return a list of vertices that forms a path including `start` and `end` vertices or null if these is no such path.
     */
    public List<V> getPath(V start, V end)
    {
        if (!edges.containsKey(start)) throw new NoSuchElementException("Graph doesn't contain vertex: "+start.toString());
        if (!edges.containsKey(end)) throw new NoSuchElementException("Graph doesn't contain vertex: "+end.toString());
        if (start.equals(end)) return Collections.singletonList(start);

        class Visit {
            private final int path;  //may be used in the future
            private final V prev;
            private Visit(int path, V prev) {
                this.path = path;
                this.prev = prev;
            }
        }

        HashMap<V, Visit> visits = new HashMap<>();
        ArrayList<V> queue = new ArrayList<>();

        visits.put(start, new Visit(0, null));
        queue.add(start);

        for (int i=0; i<queue.size(); i++) {
            V v = queue.get(i);
            Visit visit = visits.get(v);
            HashSet<V> neighbors = edges.get(v);
            Visit nextVisit = new Visit(visit.path+1, v);
            for (V neighbor : neighbors) {
                if (neighbor == end) {
                    ArrayList<V> result = new ArrayList<>();
                    result.add(end);
                    for (V u = v; u != null; u = visits.get(u).prev) {
                        result.add(u);
                    }
                    Collections.reverse(result);
                    return result;
                }
                if (visits.putIfAbsent(neighbor, nextVisit) == null) {
                    queue.add(neighbor);
                }
            }
        }
        return null;
    }
}
