package home.data_structure.graph;

import java.util.Optional;

public interface Edge<T> {
    Vertex<T> getSource();
    Vertex<T> getDestination();
    boolean isDirected();
    boolean isLoop();
    Optional<Vertex<T>> getAdjacent(Vertex<T> vertex);
}