package home.data_structure.path_search;

import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import home.data_structure.graph.Graph;
import home.data_structure.graph.GraphImpl;
import home.data_structure.graph.Path;
import home.data_structure.graph.Vertex;
import home.data_structure.graph.VertexImpl;
import home.data_structure.graph.path_search.PathSearchUtil;

public class PathSearchUtilTest {

    Graph<String> graph;
    Vertex<String> start;
    Vertex<String> end;

    @BeforeAll
    public void setup () {

        graph = new GraphImpl<>(false);
        Vertex<String> a = VertexImpl.of("a");
        Vertex<String> b = VertexImpl.of("a");
        Vertex<String> c = VertexImpl.of("a");
        Vertex<String> d = VertexImpl.of("a");

        graph.addEdge(a, b);
        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(a, c);
        graph.addEdge(a, d);
        graph.addEdge(b, d);
        graph.addEdge(c, d);

    }
    @AfterAll
    public void tearDown() {
        graph = null;
    }

    @Test public void thereIsNoSolution () {
        Predicate<Path<String>> mustVisitEveryVertices = (path) -> path.getVisitedVertices().containsAll(graph.getVertices());
        start = graph.getVertices().stream().filter(v -> "a".equals(v.getValue())).findAny().get();
        end = start;
        Set<Path<String>> result = PathSearchUtil.create(graph, start, end)
            .condition()
            .setCompleteCondition(mustVisitEveryVertices)
            .and()
            .search();
        
        Assertions.assertEquals(result.size(), 0);
    }
}
