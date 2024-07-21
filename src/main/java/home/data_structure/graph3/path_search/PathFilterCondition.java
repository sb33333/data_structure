package home.data_structure.graph3.path_search;

import home.data_structure.graph3.Edge;
import home.data_structure.graph3.Path;
import home.data_structure.graph3.Vertex;

@FunctionalInterface
public interface PathFilterCondition<T> {
    boolean test(Path<T> path, Edge<T> edge, Vertex<T> start);
}