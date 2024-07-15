package home.data_structure.graph.path_search;

import home.data_structure.graph.Edge;
import home.data_structure.graph.Path;
import home.data_structure.graph.Vertex;

@FunctionalInterface
public interface PathFilterCondition<T> {
    boolean test(Path<T> path, Edge<T> edge, Vertex<T> start);
}