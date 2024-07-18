package home.data_structure.graph;

public abstract class AbstractWeightedEdge<T> implements Edge<T>, Weighted {
    protected final Vertex<T> source;
    protected final Vertex<T> destination;
    protected final boolean directed;
    protected final double weight;
    
    public AbstractWeightedEdge(Vertex<T> source, Vertex<T> destination, boolean directed, double weight) {
        this.source = source;
        this.destination=destination;
        this.directed=directed;
        this.weight = weight;
    }
    @Override
    public Vertex<T> getSource () {
        return this.source;
    }
    @Override
    public Vertex<T> getDestination() {
        return this.destination;
    }
    @Override
    public boolean isDirected() {
        return this.directed;
    }
    @Override
    public double getWeight() {
        return this.weight;
    }
    @Override
    public boolean isLoop() {
        return this.source == this.destination;
    }
}