package home.data_structure.graph3.edge;

import java.util.Optional;
import java.util.function.BiFunction;

import home.data_structure.graph3.Vertex;
import home.data_structure.graph3.Edge;

public abstract class AbstractEdge<T> implements Edge<T> {

    protected final Vertex<T> source;
    protected final Vertex<T> destination;
    protected final boolean directed;
    protected final double weight;

    private final BiFunction<Edge<T>, Vertex<T>, Optional<Vertex<T>>> getAdjacentInternal;

    /**
     *
     */
    public AbstractEdge(Vertex<T> source, Vertex<T> destination, boolean directed) {
        this.source = source;
        this.destination = destination;
        this.directed = directed;
        this.weight = 0;
        if (directed) {
            getAdjacentInternal=(edge, vertex) -> {
                if (vertex.equals(edge.getSource())) return Optional.of(edge.getDestination());
                else return Optional.empty();
            };
        } else {
            getAdjacentInternal=(edge, vertex) -> {
                if (vertex.equals(edge.getSource())) return Optional.of(edge.getDestination());
                else if (vertex.equals(edge.getDestination())) return Optional.of(edge.getSource());
                else return Optional.empty();
            };
        }
    }

    public AbstractEdge(Vertex<T> source, Vertex<T> destination, boolean directed, double weight) {
        this.source = source;
        this.destination = destination;
        this.directed = directed;
        this.weight = weight;
        if (directed) {
            getAdjacentInternal=(edge, vertex) -> {
                if (vertex.equals(edge.getSource())) return Optional.of(edge.getDestination());
                else return Optional.empty();
            };
        } else {
            getAdjacentInternal=(edge, vertex) -> {
                if (vertex.equals(edge.getSource())) return Optional.of(edge.getDestination());
                else if (vertex.equals(edge.getDestination())) return Optional.of(edge.getSource());
                else return Optional.empty();
            };
        }
    }

    @Override
    public Optional<Vertex<T>> getAdjacent(Vertex<T> vertex) {
        return getAdjacentInternal.apply(this, vertex);
    }

    @Override
    public Vertex<T> getDestination() {
        return this.destination;
    }

    @Override
    public Vertex<T> getSource() {
        return this.source;
    }

    @Override
    public boolean isDirected() {
        return this.directed;
    }

    @Override
    public boolean isLoop() {
        return this.source == this.destination;
    }

    @Override
    public double getWeight() {
        return weight;
    }
    
}
