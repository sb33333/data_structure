package home.data_structure.graph3;

import java.util.Optional;

import home.data_structure.graph.Vertex;

public interface Edge<T> {

    Vertex<T> getSource();
    Vertex<T> getDestination();
    boolean isDirected();
    boolean isLoop();
    Optional<Vertex<T>> getAdjacent(Vertex<T> vertex);

}
