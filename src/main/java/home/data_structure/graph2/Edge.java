package home.data_structure.graph2;

import java.util.Optional;

import home.data_structure.graph.Vertex;

public interface Edge<T> {

    Vertex<T> getSource();
    Vertex<T> getDestination();
    double getWeight();
    boolean isLoop();
    Optional<Vertex<T>> getAdjacent(Vertex<T> vertex);

}
