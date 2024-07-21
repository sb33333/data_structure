package home.data_structure.graph2.edge;

import java.util.Optional;

import home.data_structure.graph3.Vertex;
import home.data_structure.graph2.Edge;

public class EdgeOperator {

    public static <T> Optional<Vertex<T>> GET_ADJACENT_ON_DIRECTED_EDGE (Edge<T> edge, Vertex<T> vertex) {
        if (vertex.equals(edge.getSource())) return Optional.of(edge.getDestination());
        else return Optional.empty();
    }
    public static <T> Optional<Vertex<T>> GET_ADJACENT_ON_UNDIRECTED_EDGE (Edge<T> edge, Vertex<T> vertex) {
        if (vertex.equals(edge.getSource())) return Optional.of(edge.getDestination());
        else if (vertex.equals(edge.getDestination())) return Optional.of(edge.getSource());
        else return Optional.empty();
    }
}
