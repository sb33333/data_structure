package home.data_structure.graph2.edge;

import java.util.Optional;

import home.data_structure.graph3.Vertex;

public class WeightedDirectedEdge<T> extends AbstractEdge<T> {

    private final double weight;
    public WeightedDirectedEdge(Vertex<T> source, Vertex<T> destination, double weight) {
        super(source, destination);
        this.weight = weight;
    }

    @Override
    public Optional<Vertex<T>> getAdjacent(Vertex<T> vertex) {
        return EdgeOperator.GET_ADJACENT_ON_DIRECTED_EDGE(this, vertex);
    }

    @Override
    public double getWeight() {
        return weight;
    }

    
}
