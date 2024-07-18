package home.data_structure.graph.path_search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Predicate;

import home.data_structure.graph.Edge;
import home.data_structure.graph.Graph;
import home.data_structure.graph.Path;
import home.data_structure.graph.PathImpl;
import home.data_structure.graph.Vertex;
/**
 * find a path from one vertex to another
 */
public class PathSearch {
    private PathSearch() {}  // not allowed

    /**
     * Finds paths using Breadth-First Search (BFS) in the given graph.
     *
     * @param graph The graph to search in
     * @param start The starting vertex
     * @param end The ending vertex
     * @param searchGoal The goal criteria for the search
     * @param edgeFilter Filter to determine which edges can be used
     * @param completeCondition Condition to consider a path complete
     * @param abortCondition Condition to abort the search
     * @param <T> The type of the vertices in the graph
     * @return A callable that, when executed, returns a set of paths meeting the search criteria
     */
    public static <T> Callable<Set<Path<T>>> findPathBFS(
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
          Consumer<Path<T>> collector = result::add;
          breadthFirstSearch(graph, start, end, searchGoal, collector, edgeFilter, completeCondition, abortCondition);
          return result;
        };
    }
    
    /**
     * Finds paths using Depth-First Search (DFS) in the given graph.
     *
     * @param graph The graph to search in
     * @param start The starting vertex
     * @param end The ending vertex
     * @param searchGoal The goal criteria for the search
     * @param edgeFilter Filter to determine which edges can be used
     * @param completeCondition Condition to consider a path complete
     * @param abortCondition Condition to abort the search
     * @param <T> The type of the vertices in the graph
     * @return A callable that, when executed, returns a set of paths meeting the search criteria
     */
    public static <T> Callable<Set<Path<T>>> findPathDFS(
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
          Consumer<Path<T>> collector = result::add;
          recursiveSearch(new PathImpl<>(start), graph, start, end, searchGoal, collector, edgeFilter, completeCondition, abortCondition);
          return result;
        };
    }
    
     /**
     * Performs recursive Depth-First Search (DFS) in the given graph.
     *
     * @param path The current path being explored
     * @param graph The graph to search in
     * @param start The starting vertex
     * @param end The ending vertex
     * @param searchGoal The goal criteria for the search
     * @param pathCollector Collector to gather valid paths
     * @param edgeFilter Filter to determine which edges can be used
     * @param completeCondition Condition to consider a path complete
     * @param abortCondition Condition to abort the search
     * @param <T> The type of the vertices in the graph
     */
    private static <T> void recursiveSearch(
        Path<T> path,
        Graph<T> graph,
        Vertex<T> start,
        Vertex<T> end,
        SearchGoal<T> searchGoal,
        Consumer<Path<T>> pathCollector,
        PathFilterCondition<T> edgeFilter,
        Predicate<Path<T>> completeCondition,
        Predicate<Path<T>> abortCondition
    ){
        if(searchGoal.test(start, end) && completeCondition.test(path)) {
            pathCollector.accept(path);
            // System.out.println(1); // TODO debug
        }
        if(abortCondition.test(path)) {
            // System.out.println(2); // TODO debug
            return; // abort search
        }
        Set<Edge<T>> connectedEdges = graph.getConnectedEdges(start);
        if(connectedEdges.isEmpty()) {
            // System.out.println(3); // TODO debug
            return; // no searchable connection
        }
        connectedEdges.forEach(connected -> connected.getAdjacent(start)
                .ifPresent(newStart -> {
                    if(!edgeFilter.test(path, connected, newStart)) return; // break;
                    Path<T> fork = path.addEdge(connected);
                    recursiveSearch(fork, graph, newStart, end, searchGoal, pathCollector, edgeFilter, completeCondition, abortCondition);
                }));
    }
    
     /**
     * Performs Breadth-First Search (BFS) in the given graph.
     *
     * @param graph The graph to search in
     * @param start The starting vertex
     * @param end The ending vertex
     * @param searchGoal The goal criteria for the search
     * @param pathCollector Collector to gather valid paths
     * @param edgeFilter Filter to determine which edges can be used
     * @param completeCondition Condition to consider a path complete
     * @param abortCondition Condition to abort the search
     * @param <T> The type of the vertices in the graph
     */
    private static <T> void breadthFirstSearch (
        Graph<T> graph,
        Vertex<T> start,
        Vertex<T> end,
        SearchGoal<T> searchGoal,
        Consumer<Path<T>> pathCollector,
        PathFilterCondition<T> edgeFilter,
        Predicate<Path<T>> completeCondition,
        Predicate<Path<T>> abortCondition   
    ) {
        Queue<Path<T>> opened = new LinkedList<>();
        Path<T> path = new PathImpl<> (start);
        
        opened.offer(path);
        while (!opened.isEmpty()) {
            Path<T> head=opened.poll();
            Vertex<T> lastNode = head.getEnd();
            if(searchGoal.test(lastNode, end) && completeCondition.test(head)) {
                // System.out.println(1); // TODO debug
                pathCollector.accept(head);
            }
            if(abortCondition.test(head)) {
                
                //System.out.println(2); // TODO debug
                continue; // abort search
            }
            Set<Edge<T>> connectedEdges = graph.getConnectedEdges(lastNode);
            connectedEdges.stream()
                .filter(edge -> edge.getAdjacent(lastNode).isPresent())
                .filter(edge -> edgeFilter.test(head, edge, lastNode))
                .forEach(edge -> opened.offer(head.addEdge(edge)))
            ;
        }
    }
}