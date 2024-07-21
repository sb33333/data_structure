package home.data_structure.graph3.edge;

import home.data_structure.graph3.Vertex;
import home.data_structure.graph3.Edge;

public class EdgeImpl<T> extends AbstractEdge<T>{

    public EdgeImpl(Vertex<T> source, Vertex<T> destination, boolean directed) {
        super(source, destination, directed);
    }

    public EdgeImpl(Vertex<T> source, Vertex<T> destination, boolean directed, double weight) {
        super(source, destination, directed, weight);
    }

    public static <T> Edge<T> directed(Vertex<T> source, Vertex<T> destination) {
        return new EdgeImpl<>(source, destination, true);
    }
    public static <T> Edge<T> directed(Vertex<T> source, Vertex<T> destination, double weight) {
        return new EdgeImpl<>(source, destination, true, weight);
    }
    public static <T> Edge<T> undirected(Vertex<T> source, Vertex<T> destination) {
        return new EdgeImpl<>(source, destination, false);
    }
    public static <T> Edge<T> undirected(Vertex<T> source, Vertex<T> destination, double weight) {
        return new EdgeImpl<>(source, destination, false, weight);
    }
    
    
}
