package home.data_structure.graph.path_search;

import home.data_structure.graph.Vertex;

@FunctionalInterface
public interface SearchGoal<T> {
    boolean test (Vertex<T> vertex, Vertex<T> goal);
}