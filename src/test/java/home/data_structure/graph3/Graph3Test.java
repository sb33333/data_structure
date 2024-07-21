package home.data_structure.graph3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import home.data_structure.graph3.vertex.VertexImpl;
import home.data_structure.graph3.edge.EdgeImpl;
import org.junit.jupiter.api.Test;

public class Graph3Test {

    Graph<String> graph;
    Vertex<String> a = VertexImpl.of("a");
    Vertex<String> b = VertexImpl.of("b");
    Vertex<String> c = VertexImpl.of("c");
    Vertex<String> d = VertexImpl.of("d");


    Edge<String> e1 = EdgeImpl.directed(a, b);
    Edge<String> e2 = EdgeImpl.directed(a, c);
    Edge<String> e3 = EdgeImpl.directed(a, d);
    Edge<String> e4 = EdgeImpl.undirected(b, d);

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void test1() {
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);
        graph.addEdge(e4);
    }


}
