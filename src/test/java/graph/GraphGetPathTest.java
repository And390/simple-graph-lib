package graph;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;


public class GraphGetPathTest {

    @Test(expected = NoSuchElementException.class)
    public void emptyGraph() {
        Graph<Integer> g = new DirectedGraph<>();
        g.getPath(1, 2);
    }

    @Test(expected = NoSuchElementException.class)
    public void singleVertex() {
        Graph<Integer> g = new DirectedGraph<>();
        g.addVertex(1);
        g.getPath(1, 2);
    }

    @Test
    public void notConnectedVertices() {
        Graph<Integer> g = new DirectedGraph<>();
        g.addVertex(1);
        g.addVertex(2);
        assertNull(g.getPath(1, 2));
    }

    @Test
    public void twoConnectedVertices() {
        Graph<Integer> g = new DirectedGraph<>();
        g.addVertex(1);
        g.addVertex(2);
        g.addEdge(1, 2);
        assertEquals(Arrays.asList(1, 2), g.getPath(1, 2));
    }

    @Test
    public void noPathWithWrongDirection() {
        Graph<Integer> g = new DirectedGraph<>();
        g.addVertex(1);
        g.addVertex(2);
        g.addEdge(2, 1);
        assertNull(g.getPath(1, 2));
    }

    @Test
    public void undirectedGraphEdgesWorksInTwoDirections() {
        Graph<Integer> g = new UndirectedGraph<>();
        g.addVertex(1);
        g.addVertex(2);
        g.addEdge(2, 1);
        assertEquals(Arrays.asList(1, 2), g.getPath(1, 2));
        assertEquals(Arrays.asList(2, 1), g.getPath(2, 1));
    }

    @Test
    public void simpleTree() {
        Graph<Integer> g = new DirectedGraph<>();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);
        g.addVertex(7);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(3, 6);
        g.addEdge(3, 7);
        assertEquals(Arrays.asList(1, 3, 6), g.getPath(1, 6));
    }

    @Test
    public void alreadyProcessedVerticesShouldNotInterfere() {
        Graph<Integer> g = new DirectedGraph<>();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(3, 2);
        g.addEdge(3, 5);
        g.addEdge(5, 4);
        g.addEdge(5, 6);
        assertEquals(Arrays.asList(1, 3, 5, 6), g.getPath(1, 6));
    }

    @Test
    public void returnsShortestPath() {
        Graph<Integer> g = new DirectedGraph<>();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 5);
        g.addEdge(5, 4);
        assertEquals(Arrays.asList(1, 2, 4), g.getPath(1, 4));
    }
}
