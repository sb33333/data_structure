package home.data_structure.graph.path_search;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Predicate;

import home.data_structure.graph.Edge;
import home.data_structure.graph.Graph;
import home.data_structure.graph.Path;
import home.data_structure.graph.PathImpl;
import home.data_structure.graph.Vertex;

public class PathSearch {
    private PathSearch() {}  // not allowed
    public static <T> Callable<Set<Path<T>>> findPath(
            Graph<T> graph,
            Vertex<T> start,
            Vertex<T> end,
            SearchGoal<T> searchGoal,
            PathFilterCondition<T> edgeFilter,
            Predicate<Path<T>> completeCondition,
            Predicate<Path<T>> abortCondition
        ) {
            return () -> {
                Set<Path<T>> result = new HashSet<>();
                Consumer<Path<T>> collector = (path) -> result.add(path);
                depthFirstRecursiveSearch(new PathImpl<T>(start), graph, start, end, searchGoal, collector, edgeFilter, completeCondition, abortCondition);
                return result;
            };
    }
    
    private static <T> void depthFirstRecursiveSearch(
        Path<T> path,
        Graph<T> graph,
        Vertex<T> start,
        Vertex<T> end,
        SearchGoal<T> searchGoal,
        Consumer<Path<T>> pathCollector,
        PathFilterCondition<T> edgeFilter,
        Predicate<Path<T>> completeCondition,
        Predicate<Path<T>> abortCondition
        ) {
        if (searchGoal.test(start, end) && completeCondition.test(path)) {
            pathCollector.accept(path);
            // System.our.println(1); // TODO debug
        }
        if (abortCondition.test(path)) {
            // System.out.println(2); // TODO debug
            return ; // abort search
        }
        Set<Edge<T>> connectedEdges = graph.getConnectedEdges(start);
        if(connectedEdges.size() == 0) {
            // System.out.println(3); // TODO debug
            return ; // no searchable connection
        }
        
        connectedEdges.stream().forEach(connected -> {
            connected.getAdjacent(start)
            .ifPresent(newStart -> {
                if(!edgeFilter.test(path, connected, newStart)) return; //break
                Path<T> fork = path.addEdge(connected);
                depthFirstRecursiveSearch(fork, graph, newStart, end, searchGoal, pathCollector, edgeFilter, completeCondition, abortCondition);
            });
        });
    }    
}