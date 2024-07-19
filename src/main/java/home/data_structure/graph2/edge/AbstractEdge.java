package home.data_structure.graph2.edge;

import home.data_structure.graph.Vertex;
import home.data_structure.graph2.Edge;

public abstract class AbstractEdge<T> implements Edge<T>{

    private final Vertex<T> source;
    private final Vertex<T> destination;
    
    
    public AbstractEdge(Vertex<T> source, Vertex<T> destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public Vertex<T> getDestination() {
        return destination;
    }

    @Override
    public Vertex<T> getSource() {
        return source;
    }

    @Override
    public boolean isLoop() {
        return source == destination;
    }

    
}
