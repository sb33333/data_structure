package home.data_structure.graph3.edge;

import home.data_structure.graph.Vertex;


public class UnweightedEdge<T> extends AbstractEdge<T> {

    public UnweightedEdge(Vertex<T> source, Vertex<T> destination, boolean directed) {
        super(source, destination, directed);
    }
}
