package home.data_structure.path_search;

import java.util.*;

import home.data_structure.graph.*;
import org.junit.jupiter.api.*;

import home.data_structure.graph.*;
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
        result = filterDuplicatePathsByVertexOrder(result);
        List<Path<String>> sorted = result.stream().sorted(Comparator.comparing((Path<String> p) ->{
           return p.getVisitedVertices().toString(); 
        })).collect(Collectors().toList());
            
        for(Path<String> p : result) {
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
                .map(path -> path.getVisitedVertices())
                .map(pp -> {
                    List<Vertex<T>> history = p.getVisitedVertices();
                    int size = pp.size();
                    if(size != history.size()) return false;
                    boolean check = true;
                    for(int i=0;i<size;i++) {
                        if(!check) continue;
                        check = pp.get(i).equals(history.get(i));
                    }
                    return check;
                })
                .noneMatch(check -> check==true);
            if(noEquals) filtered.add(p);
        }
        return filtered;
    }
}
