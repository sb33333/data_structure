package home.data_structure.path_search;

import java.util.Comparator;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;


import home.data_structure.graph3.Edge;
import home.data_structure.graph3.Graph;
import home.data_structure.graph3.Path;
import home.data_structure.graph3.Vertex;
import home.data_structure.graph3.edge.EdgeImpl;
import home.data_structure.graph3.graphImpl.GraphImpl;
import home.data_structure.graph3.path_search.PathSearcher;
import home.data_structure.graph3.vertex.VertexImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PathSearcherTest {

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

        graph = new GraphImpl<>();
        Edge<String> e1 = EdgeImpl.undirected(a, b);
        Edge<String> e2 = EdgeImpl.undirected(a, b);
        Edge<String> e3 = EdgeImpl.undirected(a, c);
        Edge<String> e4 = EdgeImpl.undirected(a, c);
        Edge<String> e5 = EdgeImpl.undirected(a, d);
        Edge<String> e6 = EdgeImpl.undirected(b, d);
        Edge<String> e7 = EdgeImpl.undirected(c, d);

        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);
        graph.addEdge(e4);
        graph.addEdge(e5);
        graph.addEdge(e6);
        graph.addEdge(e7);


    }
    @AfterEach
    public void tearDown() {
        graph = null;
    }


    @Test public void thereIsNoSolution () {
        start = a;
        end = a;
        Set<Path<String>> result = PathSearcher.create(graph, start, end)
            .condition()
                .setCompleteCondition(mustVisitEveryEdges)
                .setAbortCondition(p -> false)
                .and()
            .search();

        Assertions.assertEquals(0, result.size());
    }

    @Test public void solveTheProblemByAddingBridge () {
        Edge<String> e8 = EdgeImpl.undirected(a, d);
        Edge<String> e9 = EdgeImpl.undirected(d, c);
        Edge<String> e10 = EdgeImpl.undirected(d, b);
        graph.addEdge(e8);
        graph.addEdge(e9);
        graph.addEdge(e10);
        start = b;
        end = b;

        Set<Path<String>> result = PathSearcher.create(graph, start, end)
                .condition()
                    .setCompleteCondition(mustVisitEveryEdges)
                    .setAbortCondition(p -> false)
                    .and()
                .search();
        result = filterDuplicatePathsByVertexOrder(result);
        List<Path<String>> sorted = result.stream()
                .sorted(Comparator.comparing((Path<String> p) -> p.getVisitedVertices().toString()))
                .toList();
            
        for(Path<String> p : sorted) {
            System.out.println(":::"+p);
        }
        Assertions.assertFalse(result.isEmpty());
    }

    /**
     * remove duplicate paths that visit vertices in the same order.
     */
    private <T> Set<Path<T>> filterDuplicatePathsByVertexOrder (Set<Path<T>> result) {

        Set<Path<T>> filtered = new HashSet<>();
        for (Path<T> p:result) {
            boolean noEquals = filtered.stream()
                .map(Path::getVisitedVertices)
                .noneMatch(pp -> {
                    List<Vertex<T>> history = p.getVisitedVertices();
                    int size = pp.size();
                    if (size != history.size()) return false;
                    boolean check = true;
                    for (int i = 0; i < size; i++) {
                        if (!check) continue;
                        check = pp.get(i).equals(history.get(i));
                    }
                    return check;
                });
            if(noEquals) filtered.add(p);
        }
        return filtered;
    }
}
