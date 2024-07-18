package home.data_structure.graph;

public abstract class AbstractEdge<T> implements Edge<T> {
    protected final Vertex<T> source;
    protected final Vertex<T> destination;
    protected final boolean directed;
    
    protected AbstractEdge(Vertex<T> source, Vertex<T> destination, boolean directed) {
        super();
        this.source =source;
        this.destination = destination;
        this.directed = directed;
    }
    @Override
    public Vertex<T> getSource () {
        return this.source;
    }
    @Override
    public Vertex<T> getDestination () {
        return this.destination;
    }
    @Override
    public boolean isDirected() {
        return this.directed;
    }
    @Override
    public boolean isLoop() {
        return this.source == this.destination;
    }
}