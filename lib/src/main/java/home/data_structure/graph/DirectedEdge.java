package home.data_structure.graph;

import java.util.Optional;

public class DirectedEdge<T> extends AbstractEdge<T> {
    public DirectedEdge(Vertex<T> source, Vertex<T> destination) {
        super(source, destination, true);
    }
    @Override 
    public Optional<Vertex<T>> getAdjacent(Vertex<T> vertex) {
        if (vertex.equals(source)) return Optional.of(destination);
        else return Optional.empty();
    }
    @Override
    public String toString() {
        return "@"+this.hashCode()+":"+source+"->"+destination;
    }

}