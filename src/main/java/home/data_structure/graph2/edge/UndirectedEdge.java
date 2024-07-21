package home.data_structure.graph2.edge;

import java.util.Optional;

import home.data_structure.graph3.Vertex;

public class UndirectedEdge<T> extends AbstractEdge<T> {

    public UndirectedEdge(Vertex<T> source, Vertex<T> destination) {
        super(source, destination);
    }

    @Override
    public Optional<Vertex<T>> getAdjacent(Vertex<T> vertex) {
        return EdgeOperator.GET_ADJACENT_ON_UNDIRECTED_EDGE(this, vertex);
    }

    @Override
    public double getWeight() {
        throw new IllegalStateException("unweighted edge");
    }

    
}
