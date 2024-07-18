package home.data_structure.path_search;

import java.util.Set;
import java.util.function.Predicate;

import home.data_structure.graph.*;
import org.junit.jupiter.api.*;

import home.data_structure.graph.path_search.PathSearchUtil;

public class PathSearchUtilTest {

    Graph<String> graph;
    Vertex<String> start;
    Vertex<String> end;
    Vertex<String> a = VertexImpl.of("a");
    Vertex<String> b = VertexImpl.of("b");
    Vertex<String> c = VertexImpl.of("c");
    Vertex<String> d = VertexImpl.of("d");
    Predicate<Path<String>> mustVisitEveryEdges = (path) -> path.getVisitedEdges().containsAll(graph.getEdges());

    @BeforeEach
    public void setup() {

        graph = new GraphImpl<>(false);
        graph.addEdge(a, b);
        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(a, c);
        graph.addEdge(a, d);
        graph.addEdge(b, d);
        graph.addEdge(c, d);

    }
    @AfterEach
    public void tearDown() {
        graph = null;
    }


    @Test public void thereIsNoSolution () {
        start = a;
        end = a;
        Set<Path<String>> result = PathSearchUtil.create(graph, start, end)
            .condition()
                .setCompleteCondition(mustVisitEveryEdges)
                .setAbortCondition(p -> false)
                .and()
            .search();

        Assertions.assertEquals(0, result.size());
    }

    @Test public void solveTheProblemByAddingBridge () {
        graph.addEdge(a, d);
        graph.addEdge(d, c);
        graph.addEdge(d, b);
        start = b;
        end = b;

        Set<Path<String>> result = PathSearchUtil.create(graph, start, end)
                .condition()
                    .setCompleteCondition(mustVisitEveryEdges)
                    .setAbortCondition(p -> false)
                    .and()
                .search();
        for(Path<String> p : result) {
            System.out.println(":::"+p);
        }
        Assertions.assertFalse(result.isEmpty());
    }
}
