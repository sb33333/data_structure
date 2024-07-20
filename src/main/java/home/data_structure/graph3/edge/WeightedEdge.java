package home.data_structure.graph3.edge;

import home.data_structure.graph.Vertex;
import home.data_structure.graph.Weighted;

public class WeightedEdge<T> extends AbstractEdge<T> implements Weighted {

    private final double weight;
    public WeightedEdge(Vertex<T> source, Vertex<T> destination, boolean directed, double weight) {
        super(source, destination, directed);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    
}
