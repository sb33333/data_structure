package home.data_structure.graph.path_search;

import home.data_structure.graph.Vertex;

/**
 * Functional interface representing a goal-checking condition in a graph search.
 *
 * @param <T> The type of the vertices in the graph.
 */
@FunctionalInterface
public interface SearchGoal<T> {
    
    /**
     * Tests whether the given vertex meets the goal condition.
     *
     * @param vertex The current vertex being checked.
     * @param goal The target vertex to be reached.
     * @return {@code true} if the current vertex meets the goal condition, otherwise {@code false}.
     */
    boolean test (Vertex<T> vertex, Vertex<T> goal);
}